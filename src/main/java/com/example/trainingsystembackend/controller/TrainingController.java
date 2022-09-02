package com.example.trainingsystembackend.controller;


import com.example.trainingsystembackend.model.*;
import com.example.trainingsystembackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.BaseStream;

@CrossOrigin(origins = {"http://localhost:3000/"})
@RestController
public class TrainingController {

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;


    @RequestMapping(value = "api/v1/saveTrainer", method = RequestMethod.POST)
    public ResponseEntity<Object> addTrainer(@RequestBody Trainer trainer) {
        trainer.getUser().setPass((new BCryptPasswordEncoder()).encode(trainer.getUser().getPass()));
        userRepository.save(trainer.getUser());
        trainerRepository.save(trainer);
        return new ResponseEntity<>(trainerRepository.findAll(), HttpStatus.CREATED);
    }
//
    @RequestMapping(value = "api/v1/listTrainer", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllTrainer() {

        return new ResponseEntity<>(trainerRepository.findAll(), HttpStatus.CREATED);
    }
//
    @RequestMapping(value = "api/v1/saveTrainee", method = RequestMethod.POST)
    public ResponseEntity<Object> addTrainee(@RequestBody Trainee trainee) {
        trainee.getUser().setPass((new BCryptPasswordEncoder()).encode(trainee.getUser().getPass()));
        trainee.setBatch(batchRepository.findById(trainee.getBatch().getBatchId()).get());
        userRepository.save(trainee.getUser());

        traineeRepository.save(trainee);
        return new ResponseEntity<>(traineeRepository.findAll(), HttpStatus.CREATED);
    }
//
    @RequestMapping(value = "api/v1/listTrainee", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllTrainee() {

        return new ResponseEntity<>(traineeRepository.findAll(), HttpStatus.CREATED);
    }
//
    @RequestMapping(value = "api/v1/saveCourse", method = RequestMethod.POST)
    public ResponseEntity<Object> addCourse(@RequestBody Course course) {
        courseRepository.save(course);
        return new ResponseEntity<>(traineeRepository.findAll(), HttpStatus.CREATED);
    }
//
    @RequestMapping(value = "api/v1/listCourse", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllCourse() {

        return new ResponseEntity<>(courseRepository.findAll(), HttpStatus.OK);
    }
//
    @RequestMapping(value = "api/v1/saveBatch", method = RequestMethod.POST)
    public ResponseEntity<Object> addBatch(@RequestBody Batch batch) {
        batchRepository.save(batch);
        return new ResponseEntity<>(traineeRepository.findAll(), HttpStatus.CREATED);
    }
//
    @RequestMapping(value = "api/v1/listBatch", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllBatch() {

        return new ResponseEntity<>(batchRepository.findAll(), HttpStatus.OK);
    }
//
    @RequestMapping(value = "api/v1/saveTopic", method = RequestMethod.POST)
    public ResponseEntity<Object> addTopic(@RequestBody Topic topic) throws Exception {
        List<Topic> existingTopics = topicRepository.findDistinctByTrainerAndDay(topic.getTrainer(), topic.getDay());


        for (int i = 0; i < existingTopics.size(); i++) {
            if (topic.getTopicStartTime().equals(existingTopics.get(i).getTopicStartTime()) || topic.getTopicEndTime().equals(existingTopics.get(i).getTopicEndTime())
                    || (topic.getTopicStartTime().compareTo(existingTopics.get(i).getTopicEndTime()) < 0
                    && existingTopics.get(i).getTopicStartTime().compareTo(topic.getTopicEndTime()) < 0)) {
                throw new Exception("Time Taken");
            }
        }


        topicRepository.save(topic);
        return new ResponseEntity<>(traineeRepository.findAll(), HttpStatus.CREATED);
    }
//
    @RequestMapping(value = "api/v1/listTopic", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllTopic() {

        return new ResponseEntity<>(topicRepository.findAll(), HttpStatus.OK);
    }
//
    @RequestMapping(value = "api/v1/postAssignment", method = RequestMethod.POST)
    public ResponseEntity<Object> addAssignment(@RequestBody Assignment assignment) {
        System.out.println(userRepository.findById(assignment.getTrainer().getUser().getId()));
        assignment.setTrainer(trainerRepository.findByUser(assignment.getTrainer().getUser()));
        Assignment result=assignmentRepository.save(assignment);
        System.out.println(result);
        return new ResponseEntity<>(assignmentRepository.findAll(), HttpStatus.CREATED);
    }
//
    @RequestMapping(value = "api/v1/listTrainersTopic", method = RequestMethod.GET)
    public ResponseEntity<Object> listTrainersTopic(@RequestParam("id") Long id) {
        Trainer currentTrainer = trainerRepository.findByUser(userRepository.findById(id).get());

        return new ResponseEntity<>(topicRepository.findByTrainer(currentTrainer), HttpStatus.CREATED);
    }
//
    //single user data
    @RequestMapping(value = "api/v1/getUserinfo/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Object> listSingleUser(@PathVariable("userId") Long id) {
        User user = userRepository.findById(id).get();
        String role = user.getRole();
        if (role.equals("TRAINER")) {
            Trainer trainer = trainerRepository.findByUser(user);
            System.out.println(trainer);
            return new ResponseEntity<>(trainer, HttpStatus.OK);
        } else if (role.equals("TRAINEE")) {
            Trainee trainee = traineeRepository.findByUser(user);
            return new ResponseEntity<>(trainee, HttpStatus.OK);
        } else {
            System.out.println(role);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "api/v1/updateTrainer/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateTrainer(@PathVariable("userId") Long id, @RequestBody Trainer trainer) {
//        System.out.println("now here");
        Trainer trainerFromDatabase = trainerRepository.findByUser(userRepository.findById(id).get());
        trainerFromDatabase.setAddress(trainer.getAddress());
        trainerFromDatabase.setAge(trainer.getAge());
        trainerFromDatabase.getUser().setUsername(trainer.getUser().getUsername());
        trainerRepository.save(trainerFromDatabase);

        return new ResponseEntity<>(trainerRepository.findAll(), HttpStatus.ACCEPTED);

    }
    //
    @RequestMapping(value = "api/v1/updateTrainee/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateTrainee(@PathVariable("userId") Long id, @RequestBody Trainee trainee) {
//        System.out.println("now here");
        Trainee traineeFromDatabase = traineeRepository.findByUser(userRepository.findById(id).get());
        traineeFromDatabase.setAddress(trainee.getAddress());
        traineeFromDatabase.setAge(trainee.getAge());
        traineeFromDatabase.getUser().setUsername(trainee.getUser().getUsername());
        traineeRepository.save(traineeFromDatabase);

        return new ResponseEntity<>(traineeRepository.findAll(), HttpStatus.ACCEPTED);

    }
    //
    @RequestMapping(value = "api/v1/getAssignmentforTrainee/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAssignmentforTrainee(@PathVariable("userId") Long id) {
        Trainee trainee = traineeRepository.findByUser(userRepository.findById(id).get());
        List<Assignment> listOfAssignments= assignmentRepository.findByBatch(trainee.getBatch());

        return new ResponseEntity<>(listOfAssignments, HttpStatus.OK);
    }
    //
    @RequestMapping(value = "api/v1/submitAssignment", method = RequestMethod.POST)
    public ResponseEntity<Object> submitAssignment(@RequestBody Evaluation evaluation) {
//        System.out.println(evaluation);
        evaluation.setTrainee(traineeRepository.findByUser(evaluation.getTrainee().getUser()));

        evaluationRepository.save(evaluation);
        return new ResponseEntity<>(evaluationRepository.findAll(), HttpStatus.OK);
    }
//
    @RequestMapping(value = "api/v1/getAssignmentAnswer/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAssignmentAnswer(@PathVariable("userId") Long id) {

        return new ResponseEntity<>(evaluationRepository.findDistinctByAssignment_Trainer_User(userRepository.findById(id).get())
                , HttpStatus.OK);
    }
//
    @RequestMapping(value = "api/v1/provideMarks/{evaluationId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> getEvaluationTrainers(@PathVariable("evaluationId") Long id,@RequestBody Evaluation evaluation) {
        Evaluation evaluationFromDatabase = evaluationRepository.findById(id).get();
        evaluationFromDatabase.setObtainedMarks(evaluation.getObtainedMarks());
        evaluationRepository.save(evaluationFromDatabase);

        return new ResponseEntity<>(evaluationFromDatabase, HttpStatus.OK);
    }
//
    @RequestMapping(value = "api/v1/getYourmarks/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getYourmarks(@PathVariable("userId") Long id) {

        return new ResponseEntity<>(evaluationRepository.findDistinctByTrainee_User(userRepository.findById(id).get())
                , HttpStatus.OK);
    }
//


}



