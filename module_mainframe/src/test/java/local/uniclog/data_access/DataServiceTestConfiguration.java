package local.uniclog.data_access;

import local.uniclog.mainframe.dao.extensions.service_ekey.repository.EsKeyRepository;
import local.uniclog.mainframe.dao.extensions.service_ekey.service.EsKeyEntityDataService;
import local.uniclog.mainframe.dao.extensions.service_ekey.service.impl.EsKeyEntityDataServiceImpl;
import local.uniclog.mainframe.dao.teamspeak.repository.TeamspeakUserRepository;
import local.uniclog.mainframe.dao.teamspeak.service.TeamspeakUserEntityDataService;
import local.uniclog.mainframe.dao.teamspeak.service.impl.TeamspeakUserEntityDataServiceImpl;
import local.uniclog.mainframe.dao.telegram.repository.TelegramMyFitnessUserRepository;
import local.uniclog.mainframe.dao.telegram.repository.TelegramTORGUserRepository;
import local.uniclog.mainframe.dao.telegram.repository.TelegramUnicBotCoreRepository;
import local.uniclog.mainframe.dao.telegram.service.TelegramMyFitnessUserEntityDataService;
import local.uniclog.mainframe.dao.telegram.service.TelegramTORGUserEntityDataService;
import local.uniclog.mainframe.dao.telegram.service.TelegramUnicBotCoreUserEntityDataService;
import local.uniclog.mainframe.dao.telegram.service.impl.TelegramMyFitnessUserEntityDataServiceImpl;
import local.uniclog.mainframe.dao.telegram.service.impl.TelegramTORGUserEntityDataServiceImpl;
import local.uniclog.mainframe.dao.telegram.service.impl.TelegramUnicBotCoreUserEntityDataServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
@RequiredArgsConstructor
public class DataServiceTestConfiguration {
    private final EsKeyRepository beanEsKeyRepository;
    private final TeamspeakUserRepository beanTeamspeakUserRepository;
    private final TelegramMyFitnessUserRepository beanTelegramMyFitnessUserRepository;
    private final TelegramTORGUserRepository beanTelegramTORGUserRepository;
    private final TelegramUnicBotCoreRepository beanTelegramUnicBotCoreRepository;

    @Bean
    public EsKeyEntityDataService entityDataService() {
        return new EsKeyEntityDataServiceImpl(beanEsKeyRepository);
    }

    @Bean("beanTeamspeakUserEntityDataServiceTest")
    public TeamspeakUserEntityDataService teamspeakEntityDataService() {
        return new TeamspeakUserEntityDataServiceImpl(beanTeamspeakUserRepository);
    }

    @Bean("beanTelegramMyFitnessUserEntityDataServiceTest")
    public TelegramMyFitnessUserEntityDataService telegramMyFitnessUserEntityDataService() {
        return new TelegramMyFitnessUserEntityDataServiceImpl(beanTelegramMyFitnessUserRepository);
    }

    @Bean("beanTelegramTORGUserEntityDataServiceTest")
    public TelegramTORGUserEntityDataService telegramTORGUserEntityDataService() {
        return new TelegramTORGUserEntityDataServiceImpl(beanTelegramTORGUserRepository);
    }

    @Bean("beanTelegramUnicBotCoreUserEntityDataServiceTest")
    public TelegramUnicBotCoreUserEntityDataService telegramUnicBotCoreUserEntityDataService() {
        return new TelegramUnicBotCoreUserEntityDataServiceImpl(beanTelegramUnicBotCoreRepository);
    }
}
