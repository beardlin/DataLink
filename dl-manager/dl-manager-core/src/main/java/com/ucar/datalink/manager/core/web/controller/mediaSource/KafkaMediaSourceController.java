package com.ucar.datalink.manager.core.web.controller.mediaSource;

import com.ucar.datalink.biz.service.MediaSourceService;
import com.ucar.datalink.domain.media.MediaSourceInfo;
import com.ucar.datalink.domain.media.MediaSourceType;
import com.ucar.datalink.domain.media.parameter.kafka.KafkaMediaSrcParameter;
import com.ucar.datalink.domain.media.parameter.zk.ZkMediaSrcParameter;
import com.ucar.datalink.manager.core.web.dto.mediaSource.KafkaMediaSourceView;
import com.ucar.datalink.manager.core.web.dto.mediaSource.ZkMediaSourceView;
import com.ucar.datalink.manager.core.web.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author XC
 */
@Controller
@RequestMapping(value = "/kafka/")
public class KafkaMediaSourceController {

    public static final Logger logger = LoggerFactory.getLogger(KafkaMediaSourceController.class);

    @Autowired
    MediaSourceService mediaSourceService;

    @RequestMapping(value = "/kafkaList")
    public ModelAndView kafkaList() {
        ModelAndView mav = new ModelAndView("kafkaMediaSource/list");
        return mav;
    }

    @RequestMapping(value = "/initKafka")
    @ResponseBody
    public Page<KafkaMediaSourceView> initZk() {
        Set<MediaSourceType> setMediaSource = new HashSet<>();
        setMediaSource.add(MediaSourceType.KAFKA);
        List<MediaSourceInfo> kafkaMediaSourceList = mediaSourceService.getListByType(setMediaSource);
        List<KafkaMediaSourceView> taskView = kafkaMediaSourceList.stream().map(i -> {
            KafkaMediaSourceView view = new KafkaMediaSourceView();
            view.setId(i.getId());
            view.setName(i.getName());
            view.setDesc(i.getDesc());
            view.setCreateTime(i.getCreateTime());
            view.getKafkaMediaSrcParameter().setMediaSourceType(i.getType());
            view.getKafkaMediaSrcParameter().setServers(((KafkaMediaSrcParameter) i.getParameterObj()).getServers());
            view.getKafkaMediaSrcParameter().setTopic(((KafkaMediaSrcParameter) i.getParameterObj()).getTopic());
            return view;
        }).collect(Collectors.toList());
        return new Page<>(taskView);
    }

    @RequestMapping(value = "/toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav = new ModelAndView("kafkaMediaSource/add");
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/doAdd")
    public String doAdd(@ModelAttribute("KafkaMediaSourceView") KafkaMediaSourceView kafkaMediaSourceView) {
        try {
            //TODO 验证服务器地址
//            checkKafkaServers(kafkaMediaSourceView);
            Boolean isSuccess = mediaSourceService.insert(buildKafkaMediaSourceInfo(kafkaMediaSourceView));
            if (isSuccess) {
                return "success";
            }
        } catch (Exception e) {
            logger.error("Add Kafka Media Source Error.", e);
            return e.getMessage();
        }
        return "fail";
    }


    private MediaSourceInfo buildKafkaMediaSourceInfo(KafkaMediaSourceView kafkaMediaSourceView) {
        MediaSourceInfo kafkaMediaSourceInfo = new MediaSourceInfo();
        kafkaMediaSourceInfo.setName(kafkaMediaSourceView.getName());
        kafkaMediaSourceInfo.setDesc(kafkaMediaSourceView.getDesc());
        kafkaMediaSourceInfo.setType(kafkaMediaSourceView.getKafkaMediaSrcParameter().getMediaSourceType());
        kafkaMediaSourceInfo.setParameter(kafkaMediaSourceView.getKafkaMediaSrcParameter().toJsonString());
        return kafkaMediaSourceInfo;
    }

}
