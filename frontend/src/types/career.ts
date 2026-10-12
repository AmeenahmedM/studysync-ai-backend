export interface CareerDomain {
  id: number;
  name: string;
  description?: string;
}

export interface JobRole {
  id: number;
  name: string;
  description?: string;
  domainId: number;
  requiredSkills?: Skill[];
}

export interface Skill {
  id: number;
  name: string;
  description?: string;
  category: 'TECHNICAL' | 'SOFT' | 'DOMAIN_SPECIFIC';
  proficiencyLevel?: string;
}

export interface CareerRoadmap {
  id: number;
  name: string;
  jobRoleId: number;
  durationDays: number;
  targetDate: string;
  completionPercentage: number;
  status: 'DRAFT' | 'ACTIVE' | 'COMPLETED' | 'ARCHIVED';
  createdAt: string;
  phases?: RoadmapPhase[];
}

export interface RoadmapPhase {
  id: number;
  name: string;
  description?: string;
  phaseNumber: number;
  startDate: string;
  endDate: string;
  completionPercentage: number;
  tasks?: RoadmapTask[];
}

export interface RoadmapTask {
  id: number;
  name: string;
  description?: string;
  taskType: 'LEARNING' | 'PRACTICE' | 'PROJECT' | 'ASSESSMENT' | 'INTERVIEW_PREP' | 'APTITUDE';
  targetDate: string;
  estimatedHours: number;
  status: 'PENDING' | 'IN_PROGRESS' | 'COMPLETED' | 'MISSED' | 'RESCHEDULED';
}

export interface CareerRoadmapRequest {
  jobRoleId: number;
  durationDays: number;
  targetDate?: string;
  dailyPreparationHours?: number;
  currentSkillLevel?: string;
  name?: string;
}

export interface SkillGapAnalysis {
  jobRoleId: number;
  jobRoleName: string;
  skillGaps: SkillGap[];
  estimatedPreparationDays: number;
  overallAssessment: string;
}

export interface SkillGap {
  skillId: number;
  skillName: string;
  requiredLevel: string;
  currentLevel: string;
  priority: number;
  estimatedDaysToLearn: number;
  recommendation: string;
}

export interface SkillGapRequest {
  jobRoleId: number;
}
