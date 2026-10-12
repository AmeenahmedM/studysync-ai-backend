export interface StudyPlan {
  id: number;
  name: string;
  description?: string;
  planType: 'ACADEMIC' | 'CAREER' | 'COMBINED';
  startDate: string;
  endDate: string;
  completionPercentage: number;
  status: 'DRAFT' | 'ACTIVE' | 'COMPLETED' | 'ARCHIVED';
  createdAt: string;
  sessions?: StudySession[];
}

export interface StudySession {
  id: number;
  topicId?: number;
  scheduledStart: string;
  scheduledEnd: string;
  actualStart?: string;
  actualEnd?: string;
  sessionType: 'LEARNING' | 'REVISION' | 'PRACTICE' | 'ASSESSMENT';
  status: 'SCHEDULED' | 'IN_PROGRESS' | 'COMPLETED' | 'MISSED' | 'RESCHEDULED';
  notes?: string;
}

export interface StudyPlanRequest {
  subjectIds: number[];
  examDate: string;
  dailyStudyHours: number;
  preferredStudyTimings?: string;
  unavailableDays?: number[];
  planName?: string;
}

export interface UpdateSessionRequest {
  scheduledStart?: string;
  scheduledEnd?: string;
  status?: string;
  notes?: string;
}
