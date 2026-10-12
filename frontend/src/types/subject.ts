export interface Subject {
  id: number;
  name: string;
  description?: string;
  difficulty?: 'EASY' | 'MEDIUM' | 'HARD';
  examinationDate?: string;
  targetScore?: number;
  completionPercentage: number;
  priority: number;
  units?: SubjectUnit[];
}

export interface SubjectUnit {
  id: number;
  name: string;
  description?: string;
  completionPercentage: number;
  topics?: Topic[];
}

export interface Topic {
  id: number;
  name: string;
  description?: string;
  difficulty?: 'EASY' | 'MEDIUM' | 'HARD';
  estimatedHours?: number;
  completionPercentage: number;
  priority: number;
}

export interface CreateSubjectRequest {
  name: string;
  description?: string;
  difficulty?: string;
  examinationDate?: string;
  targetScore?: number;
  priority?: number;
}

export interface CreateTopicRequest {
  name: string;
  description?: string;
  difficulty?: string;
  estimatedHours?: number;
  priority?: number;
}
