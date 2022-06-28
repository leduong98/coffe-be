package com.example.coffeebe.domain.services.impl.business;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.app.dtos.request.impl.SliderDto;
import com.example.coffeebe.domain.entities.business.Slider;
import com.example.coffeebe.domain.services.BaseService;
import com.example.coffeebe.domain.services.impl.BaseAbtractService;
import com.example.coffeebe.domain.utils.exception.CustomErrorMessage;
import com.example.coffeebe.domain.utils.exception.CustomException;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Log4j2
public class SliderService extends BaseAbtractService implements BaseService<Slider, Long> {

    @Override
    public Page<Slider> findAll() throws Exception {
        Page<Slider> sliders = sliderRepository.findAll(pageable);
        if (sliders.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, CustomErrorMessage.SLIDER_NOT_FOUND);
        }
        return sliders;
    }

    @Override
    public Slider findById(HttpServletRequest request, Long id) {
        return sliderRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, CustomErrorMessage.SLIDER_NOT_FOUND));
    }

    @Override
    public Slider create(HttpServletRequest request, DTO dto) {
        SliderDto sliderDto = modelMapper.map(dto, SliderDto.class);
        Slider slider = Slider.builder()
                .name(sliderDto.getName())
                .link(sliderDto.getLink())
                .status(sliderDto.getStatus())
                .build();
        sliderRepository.save(slider);
        return slider;
    }

    @Override
    public Slider update(HttpServletRequest request, Long id, DTO dto) {
        SliderDto sliderDto = modelMapper.map(dto,SliderDto.class);
        Slider slider = findById(request, id);
        slider.setName(sliderDto.getName());
        slider.setLink(sliderDto.getLink());
        slider.setStatus(sliderDto.getStatus());

        sliderRepository.save(slider);
        return slider;
    }

    @Override
    public boolean delete(HttpServletRequest request, Long id) {
        Slider slider = sliderRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, CustomErrorMessage.SLIDER_NOT_FOUND)
        );

        sliderRepository.delete(slider);
        return true;
    }

    @Override
    public Page<Slider> filter(FilterDto<Slider> dto, Pageable pageable) {
        return null;
    }

    @Override
    public List<Slider> filter(HttpServletRequest request) {
        return null;
    }
}
