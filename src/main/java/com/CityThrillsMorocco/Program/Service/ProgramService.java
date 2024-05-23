package com.CityThrillsMorocco.Program.Service;

import com.CityThrillsMorocco.Program.Model.Program;
import com.CityThrillsMorocco.Program.Model.ProgramElement;
import com.CityThrillsMorocco.Program.Repository.ProgramElementRepository;
import com.CityThrillsMorocco.Program.Repository.ProgramRepository;
import com.CityThrillsMorocco.activity.Repository.ActivityRepo;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;
    private final ActivityRepo activityService;
    private final ProgramElementRepository programElementRepository;

    public ProgramService(ProgramRepository programRepository, ActivityRepo activityService, ProgramElementRepository programElementRepository) {
        this.programRepository = programRepository;
        this.activityService = activityService;
        this.programElementRepository = programElementRepository;
    }


    public Program createProgramFromActivity(Program program) {
        // Enregistrer le programme
        Program newprogram = new Program();
        newprogram.setName(program.getName());
        newprogram.setDescription(program.getDescription());
        newprogram.setNumberOfDays(program.getNumberOfDays());
        Program savedProgram = programRepository.save(newprogram);
        // Créer les éléments de programme
        generateProgramElements(savedProgram,program);

        //setProgramElements(program);
        return savedProgram;
    }

    private void generateProgramElements(Program savedProgram,Program program) {

        for (ProgramElement programElement : program.getProgramElements()) {
            programElement.setDay("Day " + (program.getProgramElements().size() + 1));
            programElement.setProgram(savedProgram);
            programElementRepository.save(programElement);
        }

    }

    private void setProgramElements(Program program) {

        for (ProgramElement programElement : program.getProgramElements()) {
            ProgramElement element = programElementRepository.getById(programElement.getId());

            programElementRepository.save(element);

        }

    }

}
