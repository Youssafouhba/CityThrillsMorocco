import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { accountResolver } from './account.resolver';

describe('accountResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => accountResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});
