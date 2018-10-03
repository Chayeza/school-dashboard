export const enum YearOfStudy {
  ONE = 'ONE',
  TWO = 'TWO',
  THREE = 'THREE',
  PG = 'PG'
}

export interface ICoarse {
  id?: number;
  courseId?: number;
  name?: string;
  duration?: number;
  cost?: number;
  level?: YearOfStudy;
}

export const defaultValue: Readonly<ICoarse> = {};
