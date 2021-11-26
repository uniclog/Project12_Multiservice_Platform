package local.uniclog.frame_data_access;

import local.uniclog.frame_data_access.extensions.service_ekey.repository.EsKeyRepository;
import local.uniclog.frame_data_access.extensions.service_ekey.service.EsKeyEntityDataService;
import local.uniclog.frame_data_access.extensions.service_ekey.service.impl.EsKeyEntityDataServiceImpl;
import local.uniclog.frame_data_access.teamspeak.repository.TeamspeakUserRepository;
import local.uniclog.frame_data_access.teamspeak.service.TeamspeakUserEntityDataService;
import local.uniclog.frame_data_access.teamspeak.service.impl.TeamspeakUserEntityDataServiceImpl;
import local.uniclog.frame_data_access.telegram.repository.TelegramMyFitnessUserRepository;
import local.uniclog.frame_data_access.telegram.repository.TelegramTORGUserRepository;
import local.uniclog.frame_data_access.telegram.repository.TelegramUnicBotCoreRepository;
import local.uniclog.frame_data_access.telegram.service.TelegramMyFitnessUserEntityDataService;
import local.uniclog.frame_data_access.telegram.service.TelegramTORGUserEntityDataService;
import local.uniclog.frame_data_access.telegram.service.TelegramUnicBotCoreUserEntityDataService;
import local.uniclog.frame_data_access.telegram.service.impl.TelegramMyFitnessUserEntityDataServiceImpl;
import local.uniclog.frame_data_access.telegram.service.impl.TelegramTORGUserEntityDataServiceImpl;
import local.uniclog.frame_data_access.telegram.service.impl.TelegramUnicBotCoreUserEntityDataServiceImpl;
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
