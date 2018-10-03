export const enum Sex {
  MALE = 'MALE',
  FEMALE = 'FEMALE'
}

export const enum Race {
  BLACK = 'BLACK',
  WHITE = 'WHITE',
  INDIAN = 'INDIAN',
  COLORED = 'COLORED',
  OTHER = 'OTHER'
}

export interface IStudent {
  id?: number;
  studentId?: number;
  name?: string;
  surname?: string;
  sex?: Sex;
  idNumber?: number;
  race?: Race;
  nationality?: string;
  contact?: number;
}

export const defaultValue: Readonly<IStudent> = {};
