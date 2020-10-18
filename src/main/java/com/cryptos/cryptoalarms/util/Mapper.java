package com.alarms.cryptoalarms.util;

import com.alarms.cryptoalarms.domain.Alarm;
import com.alarms.cryptoalarms.domain.Crypto;
import com.alarms.cryptoalarms.dto.AlarmDto;
import com.alarms.cryptoalarms.dto.CryptoDto;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;


public final class Mapper {

    private Mapper() {}

    public static final Function<Crypto, CryptoDto> toCryptoDto = (Crypto crypto) -> {
        CryptoDto cryptoDto = new CryptoDto();
        BeanUtils.copyProperties(crypto, cryptoDto);
        return cryptoDto;
    };

    public static final Function<Alarm, AlarmDto> toAlarmDto = (Alarm alarm) -> {
        AlarmDto alarmDto = new AlarmDto();
        alarmDto.setActive(alarm.isActive());
        alarmDto.setAlarmPrice(alarm.getAlarmPrice());
        alarmDto.setReferencePrice(alarm.getRefferencePrice());
        alarmDto.setRule(alarm.getRule());
        alarmDto.setCrypto(toCryptoDto.apply(alarm.getMonitoredCrypto().getCrypto()));
        alarmDto.setVariance(getVarianceAsPercentage(alarm.getRefferencePrice(), alarmDto.getCrypto().getPrice()));

        return alarmDto;
    };

    private static double getVarianceAsPercentage(double initialValue, double currentValue) {
        return  ( 100 * ( currentValue - initialValue ) / initialValue );

    }

    public static void main(String[] args) {
        System.out.println(getVarianceAsPercentage(10, 8.7));
    }
}
