package com.inditex.zarachallenge.infrastructure;

import java.util.Optional;
import java.util.function.Consumer;

import com.inditex.zarachallenge.domain.service.SizeService;
import com.inditex.zarachallenge.infrastructure.repository.product.ProductEntity;
import com.inditex.zarachallenge.infrastructure.repository.product.ProductEntityRepository;
import com.inditex.zarachallenge.infrastructure.repository.size.SizeEntity;
import javafx.css.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.inditex.zarachallenge.infrastructure.model.ProductAvailablityEvent;

@Component
@RequiredArgsConstructor
public class KafkaListener {
	
	private final SizeService sizeService;

	@Bean
	public Consumer<Message<ProductAvailablityEvent>> KafkaConsumer() {
		return message -> {
			ProductAvailablityEvent event = message.getPayload();

			SizeEntity sizeEntity = sizeService.findSizeByProductId(event.getSkuId());
			if(sizeEntity!=null) {
				sizeEntity.setAvailability(event.isAvailability());
				sizeEntity.setLastUpdated(event.getUpdate().toLocalDateTime());
				sizeService.updateSize(sizeEntity);
			}
		};
	}

}
