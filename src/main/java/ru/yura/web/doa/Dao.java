package ru.yura.web.doa;

import ru.yura.web.dto.PersonDto;

import java.util.List;

/*
 *
 *@Data 04.05.2020
 *@autor Fedorov Yuri
 *@project Bootstrap3.1.3
 *
 */
public interface Dao <T> {
    public List<PersonDto> findAllDto();
}
