import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.BackgroundProc;
import com.example.Light;
import com.example.Reader;
import com.example.Writer;
import com.example.service.LightTraffic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LightTrafficTest {
    private LightTraffic lightTraffic;
    private Reader reader;
    private Writer writer;
    private BackgroundProc backgroundProcess;

    @BeforeEach
    public void setup() {
        reader = mock(Reader.class);
        writer = mock(Writer.class);
        backgroundProcess = mock(BackgroundProc.class);
        lightTraffic = new LightTraffic(reader, writer, backgroundProcess);
    }

    @Test
    public void testGetCurrent() {
        Light light = new Light(false, 0);
        when(reader.readConfig()).thenReturn(light);

        Light actual = lightTraffic.getCurrent();

        assertEquals(light, actual);
        verify(reader).readConfig();
    }

    @Test
    public void testChangeWithColorNumberTwo() {
        Light lightCurrent = new Light();
        lightCurrent.setColorNumber(2);

        Light lightExpected = new Light();

        when(reader.readConfig()).thenReturn(lightCurrent);
        when(writer.updateConfigFile(false, 0)).thenReturn(lightExpected);

        Light lightActual = lightTraffic.change();

        Assertions.assertEquals(lightExpected, lightActual);
    }

    @Test
    public void testChangeWithColorNumberNotTwo() {
        Light lightCurrent = new Light();
        lightCurrent.setColorNumber(1);

        Light lightExpected = new Light();

        when(reader.readConfig()).thenReturn(lightCurrent);
        when(writer.updateConfigFile(false, 2)).thenReturn(lightExpected);

        Light lightActual = lightTraffic.change();

        Assertions.assertEquals(lightExpected, lightActual);
    }

    @Test
    public void testChangeModeAutomatic() {
        Light currentConf = new Light();
        currentConf.setAuto(true);

        when(reader.readConfig()).thenReturn(currentConf);

        lightTraffic.changeMode();

        verify(backgroundProcess).stop();
        verify(writer).updateConfigFile(false, 0);
    }

    @Test
    public void testChangeModeNotAutomatic() {
        Light currentConf = new Light();
        currentConf.setAuto(false);

        when(reader.readConfig()).thenReturn(currentConf);

        lightTraffic.changeMode();

        verify(writer).updateConfigFile(true, 0);
        verify(backgroundProcess).start();
    }
}


