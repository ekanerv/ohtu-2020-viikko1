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
    Varasto saldovarasto;
    
    Varasto saldoYliVarasto;
    Varasto nollaVarasto;
    Varasto saldollinenNollavarasto;
    Varasto negSaldollinen;
    
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        saldovarasto = new Varasto(10, 8);
        
        saldoYliVarasto = new Varasto(10, 12);
        nollaVarasto = new Varasto(-100);
        saldollinenNollavarasto = new Varasto(-100, 50);
        negSaldollinen = new Varasto(10, -3);
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
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
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
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void alkusaldoMääräytyyOikein() {
        assertEquals(8, saldovarasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void alkusaldoEiYlitäTilavuutta() {
        assertEquals(10, saldoYliVarasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void negatiivinenTilavuusLuoKäyttökelvottomanVaraston() {
        assertEquals(0.0, nollaVarasto.getTilavuus(), vertailuTarkkuus);
    }
    @Test
    public void negatiivinenTilavuusLuoNollavaraston() {
        assertEquals(0.0, saldollinenNollavarasto.getTilavuus(), vertailuTarkkuus);
    }
    @Test
    public void negatiivinenAlkusaldoEiVaikutaSaldoon() {
        assertEquals(0.0, negSaldollinen.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisäysEiLisää() {
        varasto.lisaaVarastoon(-100);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void negatiivinenOttaminenEiVaikuta() {
        varasto.lisaaVarastoon(5);
        double saatuMaara = varasto.otaVarastosta(-5);
        
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void tilavuusEiYlity() {
        varasto.lisaaVarastoon(100);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void varastostaEiVoidaOttaaSaldoaEnempää() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(15);
        
        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void toStringTulostaaOikeanMerkkijonon() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }
}