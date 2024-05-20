package com.springbackend.impl;

import com.springbackend.dto.UserDTO;
import com.springbackend.entity.User;
import com.springbackend.repository.UserRepository;
import com.springbackend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String signUp(UserDTO userDTO) {
        if (userRepository.existsById(userDTO.getUserId())) {
            return "failure";

        }
        else{
            setShape(userDTO);
            User user = userDTO.toEntity();
            userRepository.save(user);
            return "success";
        }
    }



    @Override
    public UserDTO login(String userId,String password) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            return new UserDTO(userOptional.get());
        } else {
            return new UserDTO();
        }
    }


        
        
        
    private void setShape(UserDTO userDTO) {
        float[] diffs = getFloats(userDTO);
        String[] labels = new String[] {"체중", "골격근량", "체지방량"};

        //정렬
        for (int i = 0; i < diffs.length - 1; i++) {
            for (int j = 0; j < diffs.length - i - 1; j++) {
                if (diffs[j] < diffs[j + 1]) {
                    float tempDiff = diffs[j];
                    diffs[j] = diffs[j + 1];
                    diffs[j + 1] = tempDiff;

                    String tempLabel = labels[j];
                    labels[j] = labels[j + 1];
                    labels[j + 1] = tempLabel;
                }
            }
        }

        if(abs(diffs[0]-diffs[2])<=2){
            userDTO.setShape("표준형");
        }
        else if(labels[0].equals("골격근량")){
            userDTO.setShape("강인형");
        }
        else
            userDTO.setShape("비만형");
    }

    private static float[] getFloats(UserDTO userDTO) {
        float stWeight = userDTO.getGender().equals("남") ? (float)pow(userDTO.getHeight()/100,2)*22 : (float)pow(userDTO.getHeight()/100,2)*21 ;
        float stMM = userDTO.getWeight()*0.45f;
        float stFP = userDTO.getGender().equals("남") ? stWeight*0.85f: stWeight*0.77f;

        float diffWeight = ((userDTO.getWeight()-stWeight)/stWeight)/0.15f;
        float diffMM = ((userDTO.getMuscleMass()-stMM)/stMM)/0.1f;
        float diffFP =(userDTO.getFatMass()-stFP)>=0? ((userDTO.getFatMass()-stFP)/stFP)/0.6f :
                ((userDTO.getFatMass()-stFP)/stFP)/0.2f;
        System.out.println(stWeight+"/"+stMM+"/"+stFP+"\n");
        System.out.println(diffWeight +"/"+diffMM +"/"+diffFP);

        return new float[]{diffWeight,diffMM,diffFP};
    }

}
