package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitettuKonstruktoriSaaOikeanTilavuuden() {
        varasto = new Varasto(20, 10);
        assertEquals(20, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void virheellisellaAlustuksellaKelvotonVarasto() {
        varasto = new Varasto(-2);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void virheellisellaTilavuudellaKuormitetullaKonstruktorillaKelvotonVarasto() {
        varasto = new Varasto(-3, 3);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    
    @Test
    public void konstruktoriKuormitusMahtuu() {
        varasto = new Varasto(20, 10);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void virheellisellaKonstruktorinKuormituksellaTyhjaVarasto() {
        varasto = new Varasto(20, -2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenSaldonLisaysEiToimi() {
        varasto.lisaaVarastoon(-20);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void saldonLisaysEiMeneYliMaksimiTilavuuden() {
        varasto.lisaaVarastoon(200);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOttaminenPalauttaaNollan() {
        assertEquals(0, varasto.otaVarastosta(-13), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaVoiOttaaMaksimissaanSenSaldonVerran() {
        varasto.lisaaVarastoon(20);
        assertEquals(10, varasto.otaVarastosta(200), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void merkkijonoesitysToimii() {
        assertEquals("saldo = 1.0, vielä tilaa 10.0", varasto.toString());
    }
}