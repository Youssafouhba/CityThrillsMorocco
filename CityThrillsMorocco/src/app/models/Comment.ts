import {Activity} from "./Activity";
import {User} from "./User";

export interface Comment {
  id: number,
  content: string,
  note: number,
  activity: Activity,
  user: User,
  createdDate: Date
}
