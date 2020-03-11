package soniq;

import com.soniq.domain.RequestDTO;
import com.soniq.exception.InValidSelectionException;
import com.soniq.service.OptimizeService;
import org.junit.Test;


public class CapacityTest {
    @Test
    public void testCapacity(){
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setRooms(new Integer[]{28});
        requestDTO.setJunior(6);
        requestDTO.setSenior(11);
        OptimizeService optimizeService =new OptimizeService();
        optimizeService.optimize(requestDTO);
    }
    @Test(expected = InValidSelectionException.class)
    public void testRoomValidationWhenItNumberNegative(){
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setRooms(new Integer[]{-189});
        OptimizeService optimizeService =new OptimizeService();
        optimizeService.optimize(requestDTO);
    }


    @Test(expected = InValidSelectionException.class)
    public void testRoomValidationWhenNoRoomProvided(){
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setRooms(new Integer[]{});
        OptimizeService optimizeService =new OptimizeService();
        optimizeService.optimize(requestDTO);
    }


}
