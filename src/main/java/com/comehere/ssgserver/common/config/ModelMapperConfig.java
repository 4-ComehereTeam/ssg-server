package com.comehere.ssgserver.common.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.comehere.ssgserver.member.dto.req.AddressInfoReqDTO;
import com.comehere.ssgserver.member.dto.req.JoinReqDTO;
import com.comehere.ssgserver.member.dto.req.SsgPointAgreesDTO;
import com.comehere.ssgserver.member.dto.req.SsgcomAgreesDTO;
import com.comehere.ssgserver.member.vo.req.AddressInfoVO;
import com.comehere.ssgserver.member.vo.req.JoinReqVO;
import com.comehere.ssgserver.member.vo.req.SsgPointAgreesVO;
import com.comehere.ssgserver.member.vo.req.SsgcomAgreesVO;

@Configuration
public class ModelMapperConfig {
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.getConfiguration()
				.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
				.setFieldMatchingEnabled(true);

		return modelMapper;
	}

	@Bean
	public ModelMapper joinModelMapper() {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
				.setFieldMatchingEnabled(true);

		modelMapper.createTypeMap(JoinReqVO.class, JoinReqDTO.class);

		modelMapper.addConverter(new AbstractConverter<AddressInfoVO, AddressInfoReqDTO>() {
			@Override
			protected AddressInfoReqDTO convert(AddressInfoVO source) {
				return AddressInfoReqDTO.builder()
						.zipcode(source.getZipcode())
						.address(source.getAddress())
						.detailAddress(source.getDetailAddress())
						.build();
			}
		});

		modelMapper.addConverter(new AbstractConverter<SsgcomAgreesVO, SsgcomAgreesDTO>() {
			@Override
			protected SsgcomAgreesDTO convert(SsgcomAgreesVO source) {
				return SsgcomAgreesDTO.builder()
						.ssgcomMktAgr1(source.getSsgcomMktAgr1())
						.ssgcomEmail(source.getSsgcomEmail())
						.ssgcomSms(source.getSsgcomSms())
						.build();
			}
		});

		modelMapper.addConverter(new AbstractConverter<SsgPointAgreesVO, SsgPointAgreesDTO>() {
			@Override
			protected SsgPointAgreesDTO convert(SsgPointAgreesVO source) {
				return SsgPointAgreesDTO.builder()
						.ssgPointMktAgr1(source.getSsgPointMktAgr1())
						.ssgPointMktAgr2(source.getSsgPointMktAgr2())
						.ssgPointEmail(source.getSsgPointEmail())
						.ssgPointSms(source.getSsgPointSms())
						.ssgPointMail(source.getSsgPointMail())
						.ssgPointCall(source.getSsgPointCall())
						.build();
			}
		});

		modelMapper.getTypeMap(JoinReqVO.class, JoinReqDTO.class).setConverter(context -> {
			JoinReqVO source = context.getSource();

			return JoinReqDTO.builder()
					.signinId(source.getSigninId())
					.password(source.getPassword())
					.name(source.getName())
					.phone(source.getPhone())
					.email(source.getEmail())
					.gender(source.getGender())
					.addressInfoReqDTO(modelMapper.map(source.getAddressInfoVo(), AddressInfoReqDTO.class))
					.ssgcomAgreesDTO(modelMapper.map(source.getSsgcomAgreesVo(), SsgcomAgreesDTO.class))
					.ssgPointAgreesDTO(modelMapper.map(source.getSsgPointAgreesVo(), SsgPointAgreesDTO.class))
					.build();
		});
		return modelMapper;
	}
}
