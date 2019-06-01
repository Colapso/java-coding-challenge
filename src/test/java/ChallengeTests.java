import app.ChallengeApi;
import app.HttpReq;
import dto.LangPairsDtos.LangPairContainerDto;
import dto.LangPairsDtos.LangPairDto;
import dto.TranslationRequestDtos.JsonResponseDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChallengeTests {

    @Test
    public void LangPairsCountTest() {
        ChallengeApi challengeApi = new ChallengeApi(new HttpReq());
        long numberPairs = challengeApi.getLanguagePair().getObjects().stream().count();
        assertEquals(21, numberPairs);
    }

    @Test
    public void LangPairsFirstAndSecondValueTest() {
        //the value of the API can change so the test might fail it should be done with a file with static content
        ChallengeApi challengeApi = new ChallengeApi(new HttpReq());
        List<LangPairContainerDto> langPairContainer = challengeApi.getLanguagePair().getObjects();
        LangPairDto firstPair = langPairContainer.stream().findFirst().get().getLang_pair();

        assertEquals("Portuguese", firstPair.getSource_language().name);
        assertEquals("pt", firstPair.getSource_language().shortname);
        assertEquals("English", firstPair.getTarget_language().name);
        assertEquals("en", firstPair.getTarget_language().shortname);

        LangPairDto secondPair = langPairContainer.stream().skip(1).findFirst().get().getLang_pair();

        assertEquals("English", secondPair.getSource_language().name);
        assertEquals("en", secondPair.getSource_language().shortname);
        assertEquals("Portuguese", secondPair.getTarget_language().name);
        assertEquals("pt", secondPair.getTarget_language().shortname);
    }

    @Test
    public void TranslationTest() {

        ChallengeApi challengeApi = new ChallengeApi(new HttpReq());
        JsonResponseDto responseTest = challengeApi.getTranslation("Ele", "pt", "en");

        assertEquals("new", responseTest.getStatus());
        assertEquals("pt", responseTest.getSourceLanguage());
        assertEquals("en", responseTest.getTarguetLanguage());
        assertEquals("Ele", responseTest.getText());

        JsonResponseDto responseTest2 = challengeApi.getTranslation("Hello", "en", "fr");

        assertEquals("new", responseTest2.getStatus());
        assertEquals("en", responseTest2.getSourceLanguage());
        assertEquals("fr", responseTest2.getTarguetLanguage());
        assertEquals("Hello", responseTest2.getText());

        JsonResponseDto responseTes3 = challengeApi.getTranslation("Amor", "es", "en");

        assertEquals("new", responseTes3.getStatus());
        assertEquals("es", responseTes3.getSourceLanguage());
        assertEquals("en", responseTes3.getTarguetLanguage());
        assertEquals("Amor", responseTes3.getText());
    }

    @Test
    public void TranslationCheckTest() {
        ChallengeApi challengeApi = new ChallengeApi(new HttpReq());
        JsonResponseDto responseTest = challengeApi.getTranslation("Ele", "pt", "en");
        String id = responseTest.getUid();
        assertEquals("new", responseTest.getStatus());
        assertEquals("pt", responseTest.getSourceLanguage());
        assertEquals("en", responseTest.getTarguetLanguage());
        assertEquals("Ele", responseTest.getText());

        while (!responseTest.getStatus().equals("completed") || isNull(responseTest)) {
            responseTest = challengeApi.getTranslationCheck(id);
        }
        assertEquals("completed", responseTest.getStatus());
        assertEquals("He", responseTest.getTranslatedText());
    }
}
