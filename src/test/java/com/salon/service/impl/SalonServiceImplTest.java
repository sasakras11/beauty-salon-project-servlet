package com.salon.service.impl;

import com.salon.dao.SalonDao;
import com.salon.dao.UserDao;
import com.salon.entity.User;
import com.salon.exception.ValidationException;
import com.salon.service.SalonService;
import com.salon.service.util.DataParser;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SalonServiceImplTest {


  private static final String VALID_ID = "2";
  private static final int ID = 1;
  private static final String NOT_VALID_ID = "a";

  @Mock private UserDao userDao;
 @Mock private DataParser dataParser;

  @InjectMocks private SalonServiceImpl salonService;

  private final List<User> MASTERS = Arrays.asList(
          User.UserBuilder.anUser().withPassword("alex2312321").withUsername("alexandbx23xe4214r").build(),
          User.UserBuilder.anUser().withPassword("Ivan2235").withUsername("alvdsvsde").build(),
          User.UserBuilder.anUser().withPassword("ALEXA188312").withUsername("alevdsvds").build(),
          User.UserBuilder.anUser().withPassword("Dima335753").withUsername("a65635bfnfwe").build(),
          User.UserBuilder.anUser().withPassword("alexkras231").withUsername("vinilVBr1").build());


  @After
  public void resetMocks(){
      reset(userDao,dataParser);
  }

  @Test(expected = ValidationException.class)
  public void ifSalonIdISNotIntegerShouldBeException() {
            when(dataParser.parseInt(NOT_VALID_ID)).thenReturn(Optional.empty());

            salonService.getMastersOfSalon(NOT_VALID_ID);

            verifyZeroInteractions(userDao);
            verify(dataParser,times(1)).parseInt(NOT_VALID_ID);

  }

  @Test
  public void ifSalonIdISIntegerShouldReturnSalonsList() {
    when(dataParser.parseInt(VALID_ID)).thenReturn(Optional.of(ID));
    when(userDao.findAllMasterBySalonId(ID)).thenReturn(MASTERS);

    salonService.getMastersOfSalon(VALID_ID);

    verify(dataParser,times(1)).parseInt(VALID_ID);
    verify(userDao,times(1)).findAllMasterBySalonId(ID);

  }
}
