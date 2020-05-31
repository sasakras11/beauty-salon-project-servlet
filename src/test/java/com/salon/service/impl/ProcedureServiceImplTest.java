package com.salon.service.impl;

import com.salon.dao.ProcedureDao;
import com.salon.entity.Procedure;
import com.salon.service.util.DataParser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProcedureServiceImplTest {

  private static final List<Procedure> FIRST_PAGE_PROCEDURES =
      Arrays.asList(
          Procedure.ProcedureBuilder.aProcedure().withId(1).build(),
          Procedure.ProcedureBuilder.aProcedure().withId(2).build(),
          Procedure.ProcedureBuilder.aProcedure().withId(3).build());
  private static final List<Procedure> SECOND_PAGE_PROCEDURES =
      Arrays.asList(
          Procedure.ProcedureBuilder.aProcedure().withId(4).build(),
          Procedure.ProcedureBuilder.aProcedure().withId(5).build(),
          Procedure.ProcedureBuilder.aProcedure().withId(6).build());

  public static final String TOO_BIG_PAGE = "10";
  public static final String NOT_INTEGER = "b";
  public static final String ZERO_PAGE = "0";
  public static final String PAGE_TWO = "2";

  public static final String NEGATIVE_PAGE = "-1";


  @Mock private ProcedureDao procedureDao;

  @Mock private DataParser dataParser;

  @InjectMocks private ProcedureServiceImpl procedureService;

  @After
  public void resetMocks() {
    reset(dataParser, procedureDao);
  }

  @Test
  public void ifPageIsBiggerThenMaxThenReturnFirstPage() {
    when(dataParser.parseInt(TOO_BIG_PAGE)).thenReturn(Optional.of(10));
    when(procedureDao.findAll(1, 3)).thenReturn(FIRST_PAGE_PROCEDURES);

    Assert.assertEquals(procedureService.findAll(TOO_BIG_PAGE), FIRST_PAGE_PROCEDURES);
  }

  @Test
  public void ifPageIsNegativeThenReturnFirstPage() {
    when(dataParser.parseInt(NEGATIVE_PAGE)).thenReturn(Optional.of(-1));
    when(procedureDao.findAll(1, 3)).thenReturn(FIRST_PAGE_PROCEDURES);

    Assert.assertEquals(procedureService.findAll(NEGATIVE_PAGE), FIRST_PAGE_PROCEDURES);
  }
  @Test
  public void ifPageIsZeroThenReturnFirstPage() {
    when(dataParser.parseInt(ZERO_PAGE)).thenReturn(Optional.of(0));
    when(procedureDao.findAll(1, 3)).thenReturn(FIRST_PAGE_PROCEDURES);

    Assert.assertEquals(procedureService.findAll(ZERO_PAGE), FIRST_PAGE_PROCEDURES);
  }
  @Test
  public void ifPageIsNotIntegerThenReturnFirstPage() {
    when(dataParser.parseInt(NOT_INTEGER)).thenReturn(Optional.empty());
    when(procedureDao.findAll(1, 3)).thenReturn(FIRST_PAGE_PROCEDURES);

    Assert.assertEquals(procedureService.findAll(NOT_INTEGER), FIRST_PAGE_PROCEDURES);
  }

  @Test
  public void ifPageNumberIsValidThenReturnValidPage() {

    when(dataParser.parseInt(PAGE_TWO)).thenReturn(Optional.of(2));
    when(procedureDao.count()).thenReturn(6);
    when(procedureDao.findAll(2, 3)).thenReturn(SECOND_PAGE_PROCEDURES);

    Assert.assertEquals(procedureService.findAll(PAGE_TWO), SECOND_PAGE_PROCEDURES);
  }
}
