package local.uniclog.mainframe.dao;

import local.uniclog.mainframe.dao.common.DataUtilsService;
import local.uniclog.mainframe.dao.extensions.service_ekey.repository.EsKeyRepository;
import local.uniclog.mainframe.dao.extensions.service_ekey.service.EsKeyEntityDataAccessService;
import local.uniclog.mainframe.dao.extensions.service_ekey.service.EsKeyEntityDataService;
import local.uniclog.mainframe.dao.extensions.service_ekey.service.impl.EsKeyEntityDataAccessServiceImpl;
import local.uniclog.mainframe.dao.extensions.service_ekey.service.impl.EsKeyEntityDataServiceImpl;
import local.uniclog.mainframe.dao.teamspeak.repository.TeamspeakUserRepository;
import local.uniclog.mainframe.dao.teamspeak.service.TeamspeakUserEntityDataAccessService;
import local.uniclog.mainframe.dao.teamspeak.service.TeamspeakUserEntityDataService;
import local.uniclog.mainframe.dao.teamspeak.service.impl.TeamspeakUserEntityDataAccessServiceImpl;
import local.uniclog.mainframe.dao.teamspeak.service.impl.TeamspeakUserEntityDataServiceImpl;
import local.uniclog.mainframe.dao.telegram.repository.TelegramMyFitnessUserRepository;
import local.uniclog.mainframe.dao.telegram.repository.TelegramTORGUserRepository;
import local.uniclog.mainframe.dao.telegram.repository.TelegramUnicBotCoreRepository;
import local.uniclog.mainframe.dao.telegram.service.*;
import local.uniclog.mainframe.dao.telegram.service.impl.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration(value = "testConfiguration_JpaTests")
@ComponentScan({"local.uniclog.mainframe.dao.common"})
@RequiredArgsConstructor
public class DataServiceTestConfiguration {
    private final EsKeyRepository beanEsKeyRepository;
    private final TeamspeakUserRepository beanTeamspeakUserRepository;
    private final TelegramMyFitnessUserRepository beanTelegramMyFitnessUserRepository;
    private final TelegramTORGUserRepository beanTelegramTORGUserRepository;
    private final TelegramUnicBotCoreRepository beanTelegramUnicBotCoreRepository;

    private final DataUtilsService beanDataUtilsService;

    @Bean("beanEsKeyEntityDataService")
    public EsKeyEntityDataService esKeyEntityDataService() {
        return new EsKeyEntityDataServiceImpl(beanEsKeyRepository, beanDataUtilsService);
    }

    @Bean("beanTeamspeakUserEntityDataServiceTest")
    public TeamspeakUserEntityDataService teamspeakEntityDataService() {
        return new TeamspeakUserEntityDataServiceImpl(beanTeamspeakUserRepository, beanDataUtilsService);
    }

    @Bean("beanTelegramMyFitnessUserEntityDataServiceTest")
    public TelegramMyFitnessUserEntityDataService telegramMyFitnessUserEntityDataService() {
        return new TelegramMyFitnessUserEntityDataServiceImpl(beanTelegramMyFitnessUserRepository, beanDataUtilsService);
    }

    @Bean("beanTelegramTORGUserEntityDataServiceTest")
    public TelegramTORGUserEntityDataService telegramTORGUserEntityDataService() {
        return new TelegramTORGUserEntityDataServiceImpl(beanTelegramTORGUserRepository, beanDataUtilsService);
    }

    @Bean("beanTelegramUnicBotCoreUserEntityDataServiceTest")
    public TelegramUnicBotCoreUserEntityDataService telegramUnicBotCoreUserEntityDataService() {
        return new TelegramUnicBotCoreUserEntityDataServiceImpl(beanTelegramUnicBotCoreRepository, beanDataUtilsService);
    }

    @Bean("beanEsKeyEntityDataAccessServiceTest")
    public EsKeyEntityDataAccessService esKeyEntityDataAccessService() {
        return new EsKeyEntityDataAccessServiceImpl(esKeyEntityDataService());
    }

    @Bean("beanTeamspeakUserEntityDataAccessServiceTest")
    public TeamspeakUserEntityDataAccessService teamspeakEntityDataAccessService() {
        return new TeamspeakUserEntityDataAccessServiceImpl(teamspeakEntityDataService());
    }

    @Bean("beanTelegramMyFitnessUserEntityDataAccessServiceTest")
    public TelegramMyFitnessUserEntityDataAccessService telegramMyFitnessEntityDataAccessService() {
        return new TelegramMyFitnessUserEntityDataAccessServiceImpl(telegramMyFitnessUserEntityDataService());
    }

    @Bean("beanTelegramTORGUserEntityDataAccessServiceTest")
    public TelegramTORGUserEntityDataAccessService telegramTORGEntityDataAccessService() {
        return new TelegramTORGUserEntityDataAccessServiceImpl(telegramTORGUserEntityDataService());
    }

    @Bean("beanTelegramUnicBotCoreUserEntityDataAccessServiceTest")
    public TelegramUnicBotCoreUserEntityDataAccessService telegramUnicBotCoreEntityDataAccessService() {
        return new TelegramUnicBotCoreUserEntityDataAccessServiceImpl(telegramUnicBotCoreUserEntityDataService());
    }
}
