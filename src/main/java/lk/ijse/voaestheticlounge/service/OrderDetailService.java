package lk.ijse.voaestheticlounge.service;

import lk.ijse.voaestheticlounge.dto.OrderDTO;
import lk.ijse.voaestheticlounge.dto.OrderDetailDTO;
import lk.ijse.voaestheticlounge.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    void save(OrderDetailDTO orderDetailDTO);
    void delete(Long id);
    List<OrderDetailDTO> getAllODs();
}
