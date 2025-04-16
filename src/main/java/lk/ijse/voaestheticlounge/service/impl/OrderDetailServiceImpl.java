package lk.ijse.voaestheticlounge.service.impl;

import lk.ijse.voaestheticlounge.dto.OrderDTO;
import lk.ijse.voaestheticlounge.dto.OrderDetailDTO;
import lk.ijse.voaestheticlounge.entity.OrderDetail;
import lk.ijse.voaestheticlounge.repo.OrderDetailRepository;
import lk.ijse.voaestheticlounge.repo.OrderRepository;
import lk.ijse.voaestheticlounge.service.OrderDetailService;
import lk.ijse.voaestheticlounge.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public void save(OrderDetailDTO orderDetailDTO) {
        orderRepository.save(modelMapper.map(orderDetailDTO, OrderDetail.class));
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<OrderDetailDTO> getAllODs() {
        return List.of();
    }
}
