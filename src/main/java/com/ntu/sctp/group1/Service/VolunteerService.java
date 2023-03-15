package com.ntu.sctp.group1.Service;

import com.ntu.sctp.group1.Exceptions.NoVolunteerFoundExceptions;
import com.ntu.sctp.group1.entity.Volunteer;
import com.ntu.sctp.group1.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerService {

    @Autowired
    
    VolunteerRepository volunteerRepository;

    public List<Volunteer> getAllVolunteers() throws NoVolunteerFoundExceptions {
        List<Volunteer> volunteers = volunteerRepository.findAll();
        if (volunteers.isEmpty()) {
             throw new NoVolunteerFoundExceptions("No Volunteer Found!");
        } else {
            return volunteers;
        }
    }

    public Volunteer getVolunteerById(int id) throws NoVolunteerFoundExceptions {
        Optional<Volunteer> volunteer = volunteerRepository.findById(id);
        if (volunteer.isPresent()) {
            return volunteer.get();
        } else {
            throw new NoVolunteerFoundExceptions ("Volunteer not found with ID: " + id);
        }
    }

    public Volunteer createVolunteer(Volunteer newVolunteer) throws NoVolunteerFoundExceptions {
        if (newVolunteer.getName().isEmpty()) {
            throw new NoVolunteerFoundExceptions("Volunteer's name and email cannot be empty");
        }
        return volunteerRepository.save(newVolunteer);
    }

    public Volunteer updateVolunteer(int id, Volunteer updatedVolunteer) throws NoVolunteerFoundExceptions {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new NoVolunteerFoundExceptions("Volunteer not found with ID: " + id));

        volunteer.setName(updatedVolunteer.getName());
        volunteer.setAge(updatedVolunteer.getAge());
        volunteer.setEmail(updatedVolunteer.getEmail());
        volunteer.setContact(updatedVolunteer.getContact());
        volunteer.setEducation(updatedVolunteer.getEducation());
        volunteer.setPastExperience(updatedVolunteer.getPastExperience());

        return volunteerRepository.save(volunteer);
    }
}
