package com.soniq.service;

import com.soniq.domain.RequestDTO;
import com.soniq.domain.ResponseDTO;
import com.soniq.exception.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OptimizeService {

    public List<ResponseDTO> optimize(RequestDTO optimizeDTO) {
        List<ResponseDTO> optimizationResult = new ArrayList<>();
        for ( Integer noOfRooms : optimizeDTO.getRooms() ) {
            optimizationResult.add(optimize(noOfRooms, optimizeDTO.getSenior(), optimizeDTO.getJunior()));
        }
        return optimizationResult;
    }

    @SneakyThrows
    private ResponseDTO optimize(Integer noOfRooms, int seniorCapacity, int juniorCapacity) {
        //room <= senior*seniorCapacity + junior*juniorCapacity
        int seniors = 1; //  least one Senior to lead the juniors
        int juniors = 0;
        //we know there has to be a senior so we reduce by a senior's capacity
        int remainingRooms = noOfRooms - seniors * seniorCapacity;

        while (remainingRooms > 0) {
            if (getCurrentResidueToClean(remainingRooms, seniorCapacity) > getCurrentResidueToClean(remainingRooms, juniorCapacity)) {

                //if we reached the number of remaining rooms to clean with at least one senior
                if (noOfRooms == (remainingRooms + seniorCapacity)) {
                    remainingRooms = remainingRooms - seniorCapacity;
                    seniors++;
                    break;
                } else if ((getResidueOverCapacity(remainingRooms + seniorCapacity, juniorCapacity) >=
                        getResidueOverCapacity(remainingRooms, juniorCapacity))
                        || (getResidueOverCapacity(remainingRooms, juniorCapacity) <= getResidueOverCapacity(remainingRooms, seniorCapacity))) {
                    remainingRooms = remainingRooms + seniorCapacity;
                    seniors--;
                }

                break;
            }
            remainingRooms = remainingRooms - seniorCapacity;
            seniors++;
        }

        while (remainingRooms > 0) {
            remainingRooms = remainingRooms - juniorCapacity;
            juniors++;
        }
        System.out.println("Seniors: " + seniors + ", Juniors: " + juniors);
        return new ResponseDTO(seniors, juniors);
    }


    private static int getCurrentResidueToClean(int numOfRooms, int workerCapacity) {
        return Math.abs(numOfRooms - workerCapacity);
    }


    private static int getResidueOverCapacity(int numOfRooms, int workCapacity) {
        int overCapacity = 0;
        while (numOfRooms > 0) {
            numOfRooms = numOfRooms - workCapacity;
            overCapacity = numOfRooms;
        }
        return overCapacity;
    }
}
