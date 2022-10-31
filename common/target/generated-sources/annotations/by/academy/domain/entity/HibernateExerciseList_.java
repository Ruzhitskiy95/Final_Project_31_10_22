package by.academy.domain.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HibernateExerciseList.class)
public abstract class HibernateExerciseList_ {

	public static volatile SetAttribute<HibernateExerciseList, HibernateTrainingProgram> trainingProgramFour;
	public static volatile SingularAttribute<HibernateExerciseList, String> exerciseName;
	public static volatile SetAttribute<HibernateExerciseList, HibernateTrainingProgram> trainingProgramThree;
	public static volatile SetAttribute<HibernateExerciseList, HibernateTrainingProgram> trainingProgramOne;
	public static volatile SetAttribute<HibernateExerciseList, HibernateTrainingJournal> exerciseList;
	public static volatile SingularAttribute<HibernateExerciseList, Long> id;
	public static volatile SetAttribute<HibernateExerciseList, HibernateTrainingProgram> trainingProgramTwo;

	public static final String TRAINING_PROGRAM_FOUR = "trainingProgramFour";
	public static final String EXERCISE_NAME = "exerciseName";
	public static final String TRAINING_PROGRAM_THREE = "trainingProgramThree";
	public static final String TRAINING_PROGRAM_ONE = "trainingProgramOne";
	public static final String EXERCISE_LIST = "exerciseList";
	public static final String ID = "id";
	public static final String TRAINING_PROGRAM_TWO = "trainingProgramTwo";

}

