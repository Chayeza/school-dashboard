import { Moment } from 'moment';

export interface IRegistration {
  id?: number;
  studentId?: number;
  courseId?: number;
  credits?: number;
  date?: Moment;
}

export const defaultValue: Readonly<IRegistration> = {};
