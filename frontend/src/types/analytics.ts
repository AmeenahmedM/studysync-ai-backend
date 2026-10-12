export interface AnalyticsDashboard {
  totalStudyHoursCompleted: number;
  dailyStudyMinutes: number;
  weeklyStudyMinutes: number;
  currentStreakDays: number;
  totalXp: number;
  academicCompletionPercentage: number;
  careerRoadmapCompletionPercentage: number;
  averageAssessmentScore: number;
  tasksCompletedToday: number;
  tasksScheduledToday: number;
}

export interface ProgressRecord {
  id: number;
  recordDate: string;
  dailyStudyMinutes: number;
  weeklyStudyMinutes: number;
  tasksCompleted: number;
  tasksPlanned: number;
  currentStreak: number;
  xpEarned: number;
  totalXp: number;
  createdAt: string;
}

export interface Notification {
  id: number;
  title: string;
  message: string;
  type: string;
  read: boolean;
  actionUrl?: string;
  createdAt: string;
}

export interface SimulationRequest {
  studyPlanId?: number;
  simulatedDailyHours?: number;
  simulatedExamDate?: string;
  missedDays?: number;
  changeCareerDomain?: boolean;
  newJobRoleId?: number;
}

export interface SimulationResponse {
  isSimulation: boolean;
  projectedCompletionPercentage: number;
  projectedCompletionDate: string;
  completionAssessment: 'ON_TIME' | 'DELAYED' | 'CRITICAL';
  recommendation: string;
  additionalHoursRequired: number;
}
