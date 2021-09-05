package io.github.msteiger.simpleModbus;

import static io.github.msteiger.simpleModbus.Access.*;
import static io.github.msteiger.simpleModbus.Format.*;
import static io.github.msteiger.simpleModbus.Type.*;

enum Type {
    U16(2, 0x0000),
    S16(2, 0x8000),
    U32(4, 0xFFFFFFFF),
    S32(4, 0x80000000),
    U64(8, 0xFFFFFFFFFFFFFFFFl),
    S64(8, 0x8000000000000000l),
    STR32(255, 0);

    private int length;
    private long invalid;

    Type(int length, long invalid) {
        this.length = length;
        this.invalid = invalid;
    }

    public long getInvalid() {
        return invalid;
    }

    public int getLength() {
        return length;
    }
}


enum Format {
    FIX0,
    FIX1,
    FIX2,
    FIX3,
    FIX4,
    REV,
    RAW,
    UTF8,
    FUNCTION_SEC,
    FW,
    DT,
    IP4,
    TEMP,
    TAGLIST,
    DURATION
}

enum Access {
    READ_ONLY,
    READ_WRITE,
    WRITE_ONLY
}

@SuppressWarnings({ "unused", "javadoc"})
public enum SmaCode {

    DCCFG_STRTMS("DcCfg.StrTms", 41155, 2, U32, FIX0, READ_WRITE, "s", "Start delay input", "Startverzögerung Eingang"),
    DCCFG_STRVOL("DcCfg.StrVol", 41131, 2, U32, FIX2, READ_WRITE, "V", "Minimum voltage input", "minimale Spannung Eingang"),
    DCMS_AMP("DcMs.Amp", 30769, 2, S32, FIX3, READ_ONLY, "A", "DC current input", "DC Strom Eingang"),
    DCMS_AMP_2("DcMs.Amp", 31793, 2, S32, FIX3, READ_ONLY, "A", "DC current input", "DC Strom Eingang"),
    DCMS_VOL("DcMs.Vol", 30771, 2, S32, FIX2, READ_ONLY, "V", "DC voltage input", "DC Spannung Eingang"),
    DCMS_WATT("DcMs.Watt", 30773, 2, S32, FIX0, READ_ONLY, "W", "DC power input", "DC Leistung Eingang"),
    GRIDMS_HZ("GridMs.Hz", 30803, 2, U32, FIX2, READ_ONLY, "Hz", "Grid frequency", "Netzfrequenz"),
    GRIDMS_TOTVA("GridMs.TotVA", 30813, 2, S32, FIX0, READ_ONLY, "VA", "Apparent power", "Scheinleistung"),
    GRIDMS_TOTVAR("GridMs.TotVAr", 31497, 2, S32, FIX0, READ_ONLY, "VAr", "Reactive power", "Blindleistung"),
    GRIDMS_TOTW("GridMs.TotW", 30775, 2, S32, FIX0, READ_ONLY, "W", "Power", "Leistung"),
    GRIDMS_A_PHSA("GridMs.A.phsA", 30977, 2, S32, FIX3, READ_ONLY, "A", "Grid current phase L1", "Netzstrom Phase L1"),
    GRIDMS_A_PHSB("GridMs.A.phsB", 30979, 2, S32, FIX3, READ_ONLY, "A", "Grid current phase L2", "Netzstrom Phase L2"),
    GRIDMS_A_PHSC("GridMs.A.phsC", 30981, 2, S32, FIX3, READ_ONLY, "A", "Grid current phase L3", "Netzstrom Phase L3"),
    GRIDMS_PHV_PHSA("GridMs.PhV.phsA", 30783, 2, U32, FIX2, READ_ONLY, "V", "Grid voltage phase L1", "Netzspannung Phase L1"),
    GRIDMS_PHV_PHSB("GridMs.PhV.phsB", 30785, 2, U32, FIX2, READ_ONLY, "V", "Grid voltage phase L2", "Netzspannung Phase L2"),
    GRIDMS_PHV_PHSC("GridMs.PhV.phsC", 30787, 2, U32, FIX2, READ_ONLY, "V", "Grid voltage phase L3", "Netzspannung Phase L3"),
    GRIDMS_PHV_PHSA2B("GridMs.PhV.phsA2B", 30789, 2, U32, FIX2, READ_ONLY, "V", "Grid voltage phase L1 against L2", "Netzspannung Phase L1 gegen L2"),
    GRIDMS_PHV_PHSB2C("GridMs.PhV.phsB2C", 30791, 2, U32, FIX2, READ_ONLY, "V", "Grid voltage phase L2 against L3", "Netzspannung Phase L2 gegen L3"),
    GRIDMS_PHV_PHSC2A("GridMs.PhV.phsC2A", 30793, 2, U32, FIX2, READ_ONLY, "V", "Grid voltage phase L3 against L1", "Netzspannung Phase L3 gegen L1"),
    GRIDMS_W_PHSA("GridMs.W.phsA", 30777, 2, S32, FIX0, READ_ONLY, "W", "Power L1", "Leistung L1"),
    GRIDMS_W_PHSB("GridMs.W.phsB", 30779, 2, S32, FIX0, READ_ONLY, "W", "Power L2", "Leistung L2"),
    GRIDMS_W_PHSC("GridMs.W.phsC", 30781, 2, S32, FIX0, READ_ONLY, "W", "Power L3", "Leistung L3"),
    GRIDMS_VA_PHSA("GridMs.VA.phsA", 30815, 2, S32, FIX0, READ_ONLY, "VA", "Apparent power L1", "Scheinleistung L1"),
    GRIDMS_VA_PHSB("GridMs.VA.phsB", 30817, 2, S32, FIX0, READ_ONLY, "VA", "Apparent power L2", "Scheinleistung L2"),
    GRIDMS_VA_PHSC("GridMs.VA.phsC", 30819, 2, S32, FIX0, READ_ONLY, "VA", "Apparent power L3", "Scheinleistung L3"),
    GRIDMS_VAR_PHSA("GridMs.VAr.phsA", 30807, 2, S32, FIX0, READ_ONLY, "VAr", "Reactive power L1", "Blindleistung L1"),
    GRIDMS_VAR_PHSB("GridMs.VAr.phsB", 30809, 2, S32, FIX0, READ_ONLY, "VAr", "Reactive power L2", "Blindleistung L2"),
    GRIDMS_VAR_PHSC("GridMs.VAr.phsC", 30811, 2, S32, FIX0, READ_ONLY, "VAr", "Reactive power L3", "Blindleistung L3"),
    GRIDMS_TOTPFPRC("GridMs.TotPFPrc", 30949, 2, U32, FIX3, READ_ONLY, "-", "Displacement power factor", "Verschiebungsfaktor"),
    GRIDMS_TOTPFEEI("GridMs.TotPFEEI", 31221, 2, S32, FIX3, READ_ONLY, "-", "EEI displacement power factor", "EEI-Verschiebungsfaktor"),
    ISOLATION_FLTA("Isolation.FltA", 31247, 2, S32, FIX3, READ_ONLY, "A", "Residual current", "Fehlerstrom"),
    ISOLATION_LEAKRIS("Isolation.LeakRis", 30225, 2, U32, FIX0, READ_ONLY, "Ohm", "Insulation resistance", "Isolationswiderstand"),
    METERING_TOTOPTMS_64("Metering.TotOpTms", 30521, 4, U64, DURATION, READ_ONLY, "s", "Operating time", "Betriebszeit"),
    METERING_TOTOPTMS_32("Metering.TotOpTms", 30541, 2, U32, DURATION, READ_ONLY, "s", "Operating time", "Betriebszeit"),
    METERING_TOTOPTMHSET("Metering.TotOpTmhSet", 41173, 2, U32, DURATION, READ_WRITE, "h", "Set total operating time at grid connection point", "Setze Gesamte Betriebszeit am Netzanschlusspunkt"),
    METERING_TOTFEEDTMS_64("Metering.TotFeedTms", 30525, 4, U64, DURATION, READ_ONLY, "s", "Feed-in time", "Einspeisezeit"),
    METERING_TOTFEEDTMS_32("Metering.TotFeedTms", 30543, 2, U32, DURATION, READ_ONLY, "s", "Feed-in time", "Einspeisezeit"),
    METERING_TOTWHOUT_WH_32("Metering.TotWhOut", 30513, 4, U64, FIX0, READ_ONLY, "Wh", "Total yield", "Gesamtertrag"),
    METERING_TOTWHOUT_WH_64("Metering.TotWhOut", 30529, 2, U32, FIX0, READ_ONLY, "Wh", "Total yield", "Gesamtertrag"),
    METERING_TOTWHOUT_KWH("Metering.TotWhOut", 30531, 2, U32, FIX0, READ_ONLY, "kWh", "Total yield", "Gesamtertrag"),
    METERING_TOTWHOUT_MWH("Metering.TotWhOut", 30533, 2, U32, FIX0, READ_ONLY, "MWh", "Total yield", "Gesamtertrag"),
    METERING_TOTKWHOUTSET("Metering.TotkWhOutSet", 41171, 2, U32, FIX0, READ_WRITE, "kWh", "Set total yield", "Setze Gesamtertrag"),
    NAMEPLATE_COMREV("Nameplate.ComRev", 40789, 2, U32, REV, READ_ONLY, "-", "Communication version", "Kommunikationsversion"),
    NAMEPLATE_LOCATION("Nameplate.Location", 40631, 12, STR32, UTF8, READ_WRITE, "-", "-", "-"),
    NAMEPLATE_SERNUM("Nameplate.SerNum", 30057, 2, U32, RAW, READ_ONLY, "-", "Serial number", "Seriennummer"),
    NAMEPLATE_SERNUM_2("Nameplate.SerNum", 40067, 2, U32, RAW, READ_ONLY, "-", "Serial number", "Seriennummer"),
    NAMEPLATE_MAINMODEL("Nameplate.MainModel", 30051, 2, U32, TAGLIST, READ_ONLY, "-", "Device class", "Geräteklasse"),
    NAMEPLATE_MODEL("Nameplate.Model", 30053, 2, U32, TAGLIST, READ_ONLY, "-", "Device type", "Gerätetyp"),
    NAMEPLATE_CMPMAIN_SWREV("Nameplate.CmpMain.SwRev", 40063, 2, U32, FW, READ_ONLY, "-", "Firmware version of the main processor", "Firmware-Version des Hauptprozessors"),
    OPERATION_GRISWCNT("Operation.GriSwCnt", 30599, 2, U32, FIX0, READ_ONLY, "-", "Number of grid connections", "Anzahl Netzzuschaltungen"),
    OPERATION_GRISWSTT("Operation.GriSwStt", 30217, 2, U32, TAGLIST, READ_ONLY, "-", "Grid relay/contactor", "Netzrelais/-schütz"),
    GRIDGUARD_CNTRY("GridGuard.Cntry", 40109, 2, U32, TAGLIST, READ_ONLY, "-", "Country standard set", "Eingestellter Länderdatensatz"),
    GRIDGUARD_CNTRYSET("GridGuard.CntrySet", 41121, 2, U32, FUNCTION_SEC, READ_WRITE, "-", "Set country standard", "Setze Länderdatensatz"),
    INVERTER_DCLVOL("Inverter.DclVol", 30975, 2, S32, FIX2, READ_ONLY, "V", "Intermediate circuit voltage", "Zwischenkreisspannung"),
    INVERTER_OUTPHS("Inverter.OutPhs", 40183, 2, U32, TAGLIST, READ_WRITE, "-", "Phase assignment", "Phasenzuordnung"),
    INVERTER_PLNTCTL_INTVTMSMAX("Inverter.PlntCtl.IntvTmsMax", 40791, 2, U32, FIX0, READ_WRITE, "s", "Timeout for communication fault indication", "Timeout für Kommunikationsfehlermeldung"),
    PCC_WMAX("PCC.WMax", 41217, 2, U32, FIX0, READ_WRITE, "W", "Set active power limit at grid connection point", "Eingestellte Wirkleistungsgrenze am Netzanschlusspunkt"),
    PCC_WMAXNOM("PCC.WMaxNom", 41199, 2, U32, FIX0, READ_WRITE, "%", "Set active power limit at grid connection point", "Eingestellte Wirkleistungsgrenze am Netzanschlusspunkt"),
    OPERATION_PVGRICONN("Operation.PvGriConn", 30881, 2, U32, TAGLIST, READ_ONLY, "-", "Plant mains connection", "Netzanbindung der Anlage"),
    GRIDMS_TOTA("GridMs.TotA", 30795, 2, U32, FIX3, READ_ONLY, "A", "Grid current", "Netzstrom"),
    COOLSYS_CAB_TMPVAL("Coolsys.Cab.TmpVal", 30953, 2, S32, TEMP, READ_ONLY, "°C", "Internal temperature", "Innentemperatur"),
    COOLSYS_CAB_TMPVAL_2("Coolsys.Cab.TmpVal", 34113, 2, S32, TEMP, READ_ONLY, "°C", "Internal temperature", "Innentemperatur"),
    PLNT_DCWRTG("Plnt.DcWRtg", 41203, 2, U32, FIX0, READ_WRITE, "W", "Nominal PV system power", "Anlagen-Nennleistung"),
    OPERATION_OPSTT("Operation.OpStt", 40029, 2, U32, TAGLIST, READ_ONLY, "-", "General operating status", "Allgemeiner Betriebszustand"),
    OPERATION_STANDBYSTT("Operation.StandbyStt", 33001, 2, U32, TAGLIST, READ_ONLY, "-", "Standby status", "Standby-Status"),
    OPERATION_RUNSTT("Operation.RunStt", 33003, 2, U32, TAGLIST, READ_ONLY, "-", "Operating status", "Betriebsstatus"),
    OPERATION_CTRLTYPE("Operation.CtrlType", 40007, 2, U32, TAGLIST, READ_WRITE, "-", "DC voltage control type", "Art der DC-Spannungsregelung"),
    INVERTER_FSTSTOP_WO("Inverter.FstStop", 40018, 2, U32, TAGLIST, WRITE_ONLY, "-", "Fast shut-down", "Schnellabschaltung"),
    INVERTER_FSTSTOP_RW("Inverter.FstStop", 41253, 2, U32, TAGLIST, READ_WRITE, "-", "Fast shut-down", "Schnellabschaltung"),
    OPERATION_VALRSISTL("Operation.ValRsIstl", 44021, 2, U32, FUNCTION_SEC, READ_WRITE, "-", "Reset operating data", "Betriebsdaten zurücksetzen"),
    OPERATION_DRTSTT("Operation.DrtStt", 30219, 2, U32, TAGLIST, READ_ONLY, "-", "Reason for derating", "Grund der Leistungsreduzierung"),
    INVERTER_WMODCFG_WCNSTCFG_W_RO("Inverter.WModCfg.WCnstCfg.W", 30837, 2, U32, FIX0, READ_ONLY, "W", "Active power limitation P, active power configuration", "Wirkleistungsbegrenzung in W"),
    INVERTER_WMODCFG_WCNSTCFG_W_RW("Inverter.WModCfg.WCnstCfg.W", 40212, 2, U32, FIX0, READ_WRITE, "W", "Active power limitation P, active power configuration", "Wirkleistungsbegrenzung in W"),
    INVERTER_WMODCFG_WCNSTCFG_WNOM("Inverter.WModCfg.WCnstCfg.WNom", 30839, 2, U32, FIX0, READ_ONLY, "%", "Active power limitation P, active power configuration", "Wirkleistungsbegrenzung in %"),
    INVERTER_WMODCFG_WCNSTCFG_WNOM_2("Inverter.WModCfg.WCnstCfg.WNom", 40214, 2, U32, FIX0, READ_WRITE, "%", "Active power limitation P, active power configuration", "Wirkleistungsbegrenzung in %"),
    INVERTER_WMODCFG_WCTLCOMCFG_WMAXIN("Inverter.WModCfg.WCtlComCfg.WMaxIn", 41251, 2, S32, FIX0, WRITE_ONLY, "W", "Limitation of the active power consumption in W", "Begrenzung der Leistungsaufnahme in W"),
    INVERTER_WMODCFG_WCTLCOMCFG_WSPTMAXNOM("Inverter.WModCfg.WCtlComCfg.WSptMaxNom", 44039, 2, S32, FIX2, READ_WRITE, "%", "Maximum active power", "Maximale Wirkleistung"),
    INVERTER_WMODCFG_WCTLCOMCFG_WNOMPRC_WO("Inverter.WModCfg.WCtlComCfg.WNomPrc", 40023, 1, S16, FIX2, WRITE_ONLY, "%", "Normalized active power limitation by PV system control", "Normierte Wirkleistungsbegrenzung durch Anlagensteuerung"),
    INVERTER_WMODCFG_WCTLCOMCFG_WNOMPRC_RW("Inverter.WModCfg.WCtlComCfg.WNomPrc", 41255, 1, S16, FIX2, READ_WRITE, "%", "Normalized active power limitation by PV system control", "Normierte Wirkleistungsbegrenzung durch Anlagensteuerung"),
    INVERTER_WMODCFG_WCTLCOMCFG_WNOM("Inverter.WModCfg.WCtlComCfg.WNom", 40016, 1, S16, FIX0, WRITE_ONLY, "%", "Normalized active power limitation by PV system control", "Normierte Wirkleistungsbegrenzung durch Anlagensteuerung"),
    INVERTER_WMODCFG_WCTLCOMCFG_WSPTMINNOM("Inverter.WModCfg.WCtlComCfg.WSptMinNom", 44041, 2, S32, FIX2, READ_WRITE, "%", "Minimum active power", "Minimale Wirkleistung"),
    INVERTER_WMODCFG_WCTLCOMCFG_WMAXINNOMPRC("Inverter.WModCfg.WCtlComCfg.WMaxInNomPrc", 41249, 2, S32, FIX2, WRITE_ONLY, "%", "Limitation of the active power consumption in %", "Begrenzung der Wirkleistungsaufnahme in %"),
    INVERTER_CTLCOMCFG_WCTLCOM_CTLCOMMSSMOD("Inverter.CtlComCfg.WCtlCom.CtlComMssMod", 41193, 2, U32, TAGLIST, READ_WRITE, "-", "External active power setting, fallback behavior", "Externe Wirkleistungsvorgabe, Rückfallverhalten"),
    INVERTER_CTLCOMCFG_WCTLCOM_FLBWMIN("Inverter.CtlComCfg.WCtlCom.FlbWMin", 44035, 2, S32, FIX2, READ_WRITE, "W", "External active power setting, fallback value of minimum active power", "Externe Wirkleistungsvorgabe, Rückfallwert der minimalen Wirkleistung"),
    INVERTER_CTLCOMCFG_WCTLCOM_FLBWMAX("Inverter.CtlComCfg.WCtlCom.FlbWMax", 44037, 2, S32, FIX2, READ_WRITE, "W", "External active power setting, fallback value of maximum active power", "Externe Wirkleistungsvorgabe, Rückfallwert der maximalen Wirkleistung"),
    INVERTER_CTLCOMCFG_WCTLCOM_TMSOUT("Inverter.CtlComCfg.WCtlCom.TmsOut", 41195, 2, U32, DURATION, READ_WRITE, "s", "External active power setting, timeout", "Externe Wirkleistungsvorgabe, Timeout"),
    INVERTER_WMODCFG_WCTLVOLCFG_CRV_NUMPTMAX("Inverter.WModCfg.WCtlVolCfg.Crv.NumPtMax", 33047, 2, U32, FIX0, READ_ONLY, "-", "P(U), max. number of support points", "P(U), maximale Anzahl von Stützpunkten"),
    OPERATION_VARCTL_VARMODACT("Operation.VArCtl.VArModAct", 33007, 2, U32, TAGLIST, READ_ONLY, "-", "Active reactive power range", "Aktiver Blindleistungsbereich"),
    INVERTER_VARMODCFG_VARCTLCOMCFG_VARNOMPRC_WO("Inverter.VArModCfg.VArCtlComCfg.VArNomPrc", 40022, 1, S16, FIX2, WRITE_ONLY, "%", "Extern. reactive power mode", "Externe Blindleistungsvorgabe"),
    INVERTER_VARMODCFG_VARCTLCOMCFG_VARNOMPRC_RW("Inverter.VArModCfg.VArCtlComCfg.VArNomPrc", 41256, 1, S16, FIX2, READ_WRITE, "%", "Extern. reactive power mode", "Externe Blindleistungsvorgabe"),
    INVERTER_VARMODCFG_VARCTLCOMCFG_VARNOM("Inverter.VArModCfg.VArCtlComCfg.VArNom", 40015, 1, S16, FIX1, WRITE_ONLY, "%", "Standardized reactive power setpoint by system control", "Normierte Blindleistungsvorgabe durch Anlagensteuerung"),
    INVERTER_CTLCOMCFG_VARCTLCOM_FLBVARNOM("Inverter.CtlComCfg.VArCtlCom.FlbVArNom", 41223, 2, S32, FIX2, READ_WRITE, "%", "External reactive power setting, fallback value", "Externe Blindleistungsvorgabe, Rückfallwert"),
    INVERTER_CTLCOMCFG_VARCTLCOM_CTLCOMMSSMOD("Inverter.CtlComCfg.VArCtlCom.CtlComMssMod", 41219, 2, U32, TAGLIST, READ_WRITE, "-", "External reactive power setting, fallback behavior", "Externe Blindleistungsvorgabe, Rückfallverhalten"),
    INVERTER_CTLCOMCFG_VARCTLCOM_TMSOUT("Inverter.CtlComCfg.VArCtlCom.TmsOut", 41221, 2, U32, DURATION, READ_WRITE, "s", "External reactive power setting, timeout", "Externe Blindleistungsvorgabe, Timeout"),
    INVERTER_VARMODCFG_VARCNSTCFG_VAR_RO("Inverter.VArModCfg.VArCnstCfg.VAr", 30827, 2, S32, FIX0, READ_ONLY, "VAr", "Manual reactive power setting for active power output", "Manuelle Blindleistungsvorgabe bei Wirkleistungsabgabe"),
    INVERTER_VARMODCFG_VARCNSTCFG_VAR_RW("Inverter.VArModCfg.VArCnstCfg.VAr", 40202, 2, S32, FIX0, READ_WRITE, "VAr", "Manual reactive power setting for active power output", "Manuelle Blindleistungsvorgabe bei Wirkleistungsabgabe"),
    INVERTER_VARMODCFG_VARCNSTCFG_VARNOM_RO("Inverter.VArModCfg.VArCnstCfg.VArNom", 30829, 2, S32, FIX1, READ_ONLY, "%", "Manual reactive power setting for active power output", "Manuelle Blindleistungsvorgabe bei Wirkleistungsabgabe"),
    INVERTER_VARMODCFG_VARCNSTCFG_VARNOM_RW("Inverter.VArModCfg.VArCnstCfg.VArNom", 40204, 2, S32, FIX1, READ_WRITE, "%", "Manual reactive power setting for active power output", "Manuelle Blindleistungsvorgabe bei Wirkleistungsabgabe"),
    INVERTER_VARMODCFG_VARCNSTCFGDMD_VAR("Inverter.VArModCfg.VArCnstCfgDmd.VAr", 30921, 2, S32, FIX0, READ_ONLY, "VAr", "Manual reactive power setpoint for zero power output", "Manuelle Blindleistungsvorgabe bei Nullwirkleistung"),
    INVERTER_VARMODCFG_VARCNSTCFGDMD_VARNOM("Inverter.VArModCfg.VArCnstCfgDmd.VArNom", 30923, 2, S32, FIX1, READ_ONLY, "%", "Manual reactive power setpoint for zero power output", "Manuelle Blindleistungsvorgabe bei Nullwirkleistung"),
    INVERTER_VARMODCFG_PFCTLCOMCFG_PF("Inverter.VArModCfg.PFCtlComCfg.PF", 40024, 1, U16, FIX4, WRITE_ONLY, "-", "External cos ? setpoint specification, cos ? setpoint for active power output", "Externe cos ?-Vorgabe, cos ?-Sollwert bei Wirkleistungsabgabe"),
    INVERTER_VARMODCFG_PFCTLCOMCFG_PFEXT("Inverter.VArModCfg.PFCtlComCfg.PFExt", 40025, 2, U32, TAGLIST, WRITE_ONLY, "-", "External cos ? setpoint specification, excitation type for active power output", "Externe cos ?-Vorgabe, Erregungsart bei Wirkleistungsabgabe"),
    INVERTER_VARMODCFG_PFCTLCOMCFG_PFIN("Inverter.VArModCfg.PFCtlComCfg.PFIn", 44141, 2, U32, FIX4, READ_WRITE, "-", "External cos ? setting, cos ? nominal value in case of active power draw", "Externe cos ?-Vorgabe, cos ?-Sollwert bei Wirkleistungsaufnahme"),
    INVERTER_VARMODCFG_PFCTLCOMCFG_PFEXTIN("Inverter.VArModCfg.PFCtlComCfg.PFExtIn", 44143, 2, U32, TAGLIST, READ_WRITE, "-", "External cos ? setting, excitation type in case of active power draw", "Externe cos ?-Vorgabe, Erregungsart bei Wirkleistungsaufnahme"),
    INVERTER_CTLCOMCFG_PFCTLCOM_TMSOUT("Inverter.CtlComCfg.PFCtlCom.TmsOut", 41227, 2, U32, DURATION, READ_WRITE, "s", "External cos ? setting, timeout", "Externe cos ?-Vorgabe, Timeout"),
    INVERTER_CTLCOMCFG_PFCTLCOM_TMSOUT_2("Inverter.CtlComCfg.PFCtlCom.TmsOut", 44099, 2, U32, DURATION, READ_WRITE, "s", "External cos ? setting, timeout", "Externe cos ?-Vorgabe, Timeout"),
    INVERTER_CTLCOMCFG_PFCTLCOM_CTLCOMMSSMOD("Inverter.CtlComCfg.PFCtlCom.CtlComMssMod", 41225, 2, U32, TAGLIST, READ_WRITE, "-", "External cos ? setting, fallback behavior", "Externe cos ?-Vorgabe, Rückfallverhalten"),
    INVERTER_CTLCOMCFG_PFCTLCOM_CTLCOMMSSMOD_2("Inverter.CtlComCfg.PFCtlCom.CtlComMssMod", 44097, 2, U32, TAGLIST, READ_WRITE, "-", "External cos ? setting, fallback behavior", "Externe cos ?-Vorgabe, Rückfallverhalten"),
    INVERTER_CTLCOMCFG_PFCTLCOM_FLBPF("Inverter.CtlComCfg.PFCtlCom.FlbPF", 41229, 2, S32, FIX4, READ_WRITE, "-", "External cos ? setpoint specification, fallback value of cos ? for active power output", "Externe cos ?-Vorgabe, Rückfallwert des cos ? bei Wirkleistungsabgabe"),
    INVERTER_CTLCOMCFG_PFCTLCOM_FLBPFIN("Inverter.CtlComCfg.PFCtlCom.FlbPFIn", 44115, 2, U32, FIX4, READ_WRITE, "-", "External cos ? setting, fallback value of cos ? in case of active power draw", "Externe cos ?-Vorgabe, Rückfallwert des cos ? bei Wirkleistungsaufnahme"),
    INVERTER_CTLCOMCFG_PFCTLCOM_FLBPFEXTIN("Inverter.CtlComCfg.PFCtlCom.FlbPFExtIn", 44117, 2, U32, TAGLIST, READ_WRITE, "-", "External cos ? setting, fallback value of excitation type in case of active power draw", "Externe cos ?-Vorgabe, Rückfallwert der Erregungsart bei Wirkleistungsaufnahme"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_CRV_NUMPTMAX("Inverter.VArModCfg.VArCtlVolCfg.Crv.NumPtMax", 33045, 2, U32, FIX0, READ_ONLY, "-", "Q(V), max. number of support points", "Q(U), maximale Anzahl von Stützpunkten"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_VOLREF_VOLREFPU("Inverter.VArModCfg.VArCtlVolCfg.VolRef.VolRefPu", 44211, 2, U32, FIX3, READ_WRITE, "p.u.", "Q(V), external reference voltage setting", "Q(U), Externe Bezugsspannungsvorgabe"),
    INVERTER_CTLCOMCFG_VARCTLVOLCOM_CTLCOMMSSMOD("Inverter.CtlComCfg.VArCtlVolCom.CtlComMssMod", 44193, 2, U32, TAGLIST, READ_WRITE, "-", "Q(V), fallback behavior for absent reference voltage setting", "Q(U), Rückfallverhalten bei ausbleibender Bezugsspannungsvorgabe"),
    INVERTER_CTLCOMCFG_VARCTLVOLCOM_FLBVOLREFPU("Inverter.CtlComCfg.VArCtlVolCom.FlbVolRefPu", 44195, 2, U32, FIX2, READ_WRITE, "p.u.", "Q(V), fallback of reference voltage", "Q(U), Rückfallbezugsspannung"),
    INVERTER_CTLCOMCFG_VARCTLVOLCOM_TMSOUT("Inverter.CtlComCfg.VArCtlVolCom.TmsOut", 44197, 2, U32, FIX0, READ_WRITE, "s", "Q(V), timeout for absent reference voltage setting", "Q(U), Timeout für ausbleibende Bezugsspannungsvorgabe"),
    INVERTER_VARMODCFG_PFCTLWCFG_CRV_NUMPTMAX("Inverter.VArModCfg.PFCtlWCfg.Crv.NumPtMax", 33015, 2, U32, FIX0, READ_ONLY, "-", "cos ?(P), max. number of support points", "cos ?(P), maximale Anzahl von Stützpunkten"),
    NAMEPLATE_ARTG("Nameplate.ARtg", 40480, 2, U32, FIX3, READ_ONLY, "A", "Nominal current of all phases", "Nennstrom über alle Phasen"),
    INVERTER_VAMAXOUTRTG("Inverter.VAMaxOutRtg", 33025, 2, U32, FIX0, READ_ONLY, "VA", "Rated apparent power VAMaxOutRtg", "Bemessungsscheinleistung VAMaxOutRtg"),
    INVERTER_VAMAXINRTG("Inverter.VAMaxInRtg", 33027, 2, U32, FIX0, READ_ONLY, "VA", "Rated apparent power VAMaxInRtg", "Bemessungsscheinleistung VAMaxInRtg"),
    INVERTER_WLIM("Inverter.WLim", 30231, 2, U32, FIX0, READ_ONLY, "W", "Rated active power WMaxOutRtg", "Bemessungswirkleistung WMaxOutRtg"),
    INVERTER_WMAXINRTG("Inverter.WMaxInRtg", 33019, 2, S32, FIX0, READ_ONLY, "W", "Rated active power WMaxInRtg", "Bemessungswirkleistung WMaxInRtg"),
    INVERTER_VARMAXQ1RTG("Inverter.VArMaxQ1Rtg", 33029, 2, S32, FIX0, READ_ONLY, "VAr", "Rated reactive power VArMaxQ1Rtg", "Bemessungsblindleistung VArMaxQ1Rtg"),
    INVERTER_VARMAXQ2RTG("Inverter.VArMaxQ2Rtg", 33031, 2, S32, FIX0, READ_ONLY, "VAr", "Rated reactive power VArMaxQ2Rtg", "Bemessungsblindleistung VArMaxQ2Rtg"),
    INVERTER_VARMAXQ3RTG("Inverter.VArMaxQ3Rtg", 33033, 2, S32, FIX0, READ_ONLY, "VAr", "Rated reactive power VArMaxQ3Rtg", "Bemessungsblindleistung VArMaxQ3Rtg"),
    INVERTER_VARMAXQ4RTG("Inverter.VArMaxQ4Rtg", 33035, 2, S32, FIX0, READ_ONLY, "VAr", "Rated reactive power VArMaxQ4Rtg", "Bemessungsblindleistung VArMaxQ4Rtg"),
    INVERTER_PFMINQ1RTG("Inverter.PFMinQ1Rtg", 33037, 2, U32, FIX4, READ_ONLY, "-", "Rated cos ? PFMinQ1Rtg", "Bemessungs-cos ? PFMinQ1Rtg"),
    INVERTER_PFMINQ2RTG("Inverter.PFMinQ2Rtg", 33039, 2, U32, FIX4, READ_ONLY, "-", "Rated cos ? PFMinQ2Rtg", "Bemessungs-cos ? PFMinQ2Rtg"),
    INVERTER_PFMINQ3RTG("Inverter.PFMinQ3Rtg", 33041, 2, U32, FIX4, READ_ONLY, "-", "Rated cos ? PFMinQ3Rtg", "Bemessungs-cos ? PFMinQ3Rtg"),
    INVERTER_PFMINQ4RTG("Inverter.PFMinQ4Rtg", 33043, 2, U32, FIX4, READ_ONLY, "-", "Rated cos ? PFMinQ4Rtg", "Bemessungs-cos ? PFMinQ4Rtg"),
    INVERTER_WMAX_RO("Inverter.WMax", 30233, 2, U32, FIX0, READ_ONLY, "W", "Set active power limit", "Nennwirkleistung WMaxOut"),
    INVERTER_WMAX_RW("Inverter.WMax", 40915, 2, U32, FIX0, READ_WRITE, "W", "Set active power limit", "Nennwirkleistung WMaxOut"),
    INVERTER_WMAXIN("Inverter.WMaxIn", 44383, 2, S32, FIX0, READ_WRITE, "W", "Nominal active power WMaxIn", "Nennwirkleistung WMaxIn"),
    INVERTER_VAMAXOUT("Inverter.VAMaxOut", 44389, 2, U32, FIX0, READ_WRITE, "VA", "Nominal apparent power VAMaxOut", "Nennscheinleistung VAMaxOut"),
    INVERTER_VAMAXIN("Inverter.VAMaxIn", 44391, 2, U32, FIX0, READ_WRITE, "VA", "Nominal apparent power VAMaxIn", "Nennscheinleistung VAMaxIn"),
    INVERTER_VARMAXQ1("Inverter.VArMaxQ1", 44393, 2, S32, FIX0, READ_WRITE, "VAr", "Nominal reactive power VArMaxQ1", "Nennblindleistung VArMaxQ1"),
    INVERTER_VARMAXQ2("Inverter.VArMaxQ2", 44395, 2, S32, FIX0, READ_WRITE, "VAr", "Nominal reactive power VArMaxQ2", "Nennblindleistung VArMaxQ2"),
    INVERTER_VARMAXQ3("Inverter.VArMaxQ3", 44397, 2, S32, FIX0, READ_WRITE, "VAr", "Nominal reactive power VArMaxQ3", "Nennblindleistung VArMaxQ3"),
    INVERTER_VARMAXQ4("Inverter.VArMaxQ4", 44399, 2, S32, FIX0, READ_WRITE, "VAr", "Nominal reactive power VArMaxQ4", "Nennblindleistung VArMaxQ4"),
    INVERTER_VARMAXZERWQ1("Inverter.VArMaxZerWQ1", 44401, 2, S32, FIX0, READ_WRITE, "VAr", "Nominal reactive power VArMaxZerWQ1", "Nennblindleistung VArMaxZerWQ1"),
    INVERTER_VARMAXZERWQ2("Inverter.VArMaxZerWQ2", 44403, 2, S32, FIX0, READ_WRITE, "VAr", "Nominal reactive power VArMaxZerWQ2", "Nennblindleistung VArMaxZerWQ2"),
    INVERTER_VARMAXZERWQ3("Inverter.VArMaxZerWQ3", 44405, 2, S32, FIX0, READ_WRITE, "VAr", "Nominal reactive power VArMaxZerWQ3", "Nennblindleistung VArMaxZerWQ3"),
    INVERTER_VARMAXZERWQ4("Inverter.VArMaxZerWQ4", 44407, 2, S32, FIX0, READ_WRITE, "VAr", "Nominal reactive power VArMaxZerWQ4", "Nennblindleistung VArMaxZerWQ4"),
    INVERTER_PFMINQ1("Inverter.PFMinQ1", 44409, 2, U32, FIX4, READ_WRITE, "-", "Nominal cos ? PFMinQ1", "Nenn-cos ? PFMinQ1"),
    INVERTER_PFMINQ2("Inverter.PFMinQ2", 44411, 2, U32, FIX4, READ_WRITE, "-", "Nominal cos ? PFMinQ2", "Nenn-cos ? PFMinQ2"),
    INVERTER_PFMINQ3("Inverter.PFMinQ3", 44413, 2, U32, FIX4, READ_WRITE, "-", "Nominal cos ? PFMinQ3", "Nenn-cos ? PFMinQ3"),
    INVERTER_PFMINQ4("Inverter.PFMinQ4", 44415, 2, U32, FIX4, READ_WRITE, "-", "Nominal cos ? PFMinQ4", "Nenn-cos ? PFMinQ4"),
    GRIDGUARD_CNTRY_LEAKRISMIN("GridGuard.Cntry.LeakRisMin", 41169, 2, U32, FIX0, READ_WRITE, "Ohm", "Minimum insulation resistance", "Minimaler Isolationswiderstand"),
    INVERTER_PLNTCTL_VREF("Inverter.PlntCtl.VRef", 40472, 2, U32, FIX0, READ_WRITE, "V", "Grid nominal voltage", "Netznennspannung"),
    INVERTER_WGRACONN("Inverter.WGraConn", 44001, 2, U32, FIX0, READ_WRITE, "%/min", "Soft start-up rate P", "Sanftanlaufsrate P"),
    INVERTER_WGRACONNENA("Inverter.WGraConnEna", 44003, 2, U32, TAGLIST, READ_WRITE, "-", "Soft start-up P", "Sanftanlauf P"),
    GRIDGUARD_CNTRY_GRISTRTMS("GridGuard.Cntry.GriStrTms", 44009, 2, U32, FIX3, READ_WRITE, "s", "Reconnection time upon restart", "Zuschaltzeit nach Neustart"),
    INVERTER_WGRARECON("Inverter.WGraRecon", 44011, 2, U32, FIX0, READ_WRITE, "%/min", "Reconnect gradient after grid fault", "Sanftanlaufsrate P nach Netzfehler"),
    INVERTER_WGRARECONENA("Inverter.WGraReconEna", 44013, 2, U32, TAGLIST, READ_WRITE, "-", "Soft start-up P after grid fault", "Sanftanlauf P nach Netzfehler"),
    GRIDGUARD_CNTRY_GRIFLTMONTMS("GridGuard.Cntry.GriFltMonTms", 44015, 2, U32, FIX3, READ_WRITE, "s", "Reconnection time after grid fault", "Zuschaltzeit nach Netzfehler"),
    GRIDGUARD_CNTRY_GRIFLTRECONTMS("GridGuard.Cntry.GriFltReConTms", 44017, 2, U32, FIX3, READ_WRITE, "s", "Reconnection time upon short interruption", "Schnellzuschaltzeit nach Kurzunterbrechung"),
    GRIDGUARD_CNTRY_GRIFLTTMS("GridGuard.Cntry.GriFltTms", 44019, 2, U32, FIX3, READ_WRITE, "s", "Maximum duration of a short interruption", "Maximale Dauer einer Kurzunterbrechung"),
    OPERATION_OPMOD("Operation.OpMod", 40009, 2, U32, TAGLIST, READ_WRITE, "-", "General operating mode", "Allgemeine Betriebsart"),
    INVERTER_WGRAMPP("Inverter.WGraMpp", 41201, 2, U32, FIX0, READ_WRITE, "%", "Increase rate in case of insolation change", "Anstiegsrate bei Einstrahlungsänderung"),
    INVERTER_WGRAMPP_PM("Inverter.WGraMpp", 44023, 2, U32, FIX0, READ_WRITE, "%/min", "Increase rate in case of insolation change", "Anstiegsrate bei Einstrahlungsänderung"),
    INVERTER_WMODCFG_WMOD_RO("Inverter.WModCfg.WMod", 30835, 2, U32, TAGLIST, READ_ONLY, "-", "Operating mode active power setting", "Betriebsart Wirkleistungsvorgabe"),
    INVERTER_WMODCFG_WMOD_RW("Inverter.WModCfg.WMod", 40210, 2, U32, TAGLIST, READ_WRITE, "-", "Operating mode active power setting", "Betriebsart Wirkleistungsvorgabe"),
    INVERTER_WMODCFG_WCTLCOMCFG_DYN_WTMENA("Inverter.WModCfg.WCtlComCfg.Dyn.WTmEna", 44025, 2, U32, TAGLIST, READ_WRITE, "-", "External active power setting, nominal value filter", "Externe Wirkleistungsvorgabe, Sollwertfilter"),
    INVERTER_WMODCFG_WCTLCOMCFG_DYN_WTMS("Inverter.WModCfg.WCtlComCfg.Dyn.WTms", 44027, 2, U32, FIX2, READ_WRITE, "s", "External active power setting, setting time, nominal value filter", "Externe Wirkleistungsvorgabe, Einstellzeit Sollwertfilter"),
    INVERTER_WMODCFG_WCTLCOMCFG_DYN_WGRAENA("Inverter.WModCfg.WCtlComCfg.Dyn.WGraEna", 44029, 2, U32, TAGLIST, READ_WRITE, "-", "External active power setting, limitation of change rate", "Externe Wirkleistungsvorgabe, Begrenzung der Änderungsrate"),
    INVERTER_WMODCFG_WCTLCOMCFG_DYN_WGRAPOS("Inverter.WModCfg.WCtlComCfg.Dyn.WGraPos", 44031, 2, U32, FIX2, READ_WRITE, "%/s", "External active power setting, increase rate", "Externe Wirkleistungsvorgabe, Anstiegsrate"),
    INVERTER_WMODCFG_WCTLCOMCFG_DYN_WGRANEG("Inverter.WModCfg.WCtlComCfg.Dyn.WGraNeg", 44033, 2, U32, FIX2, READ_WRITE, "%/s", "External active power setting, decrease rate", "Externe Wirkleistungsvorgabe, Absenkungsrate"),
    INVERTER_WMODCFG_WCTLVOLCFG_ENA("Inverter.WModCfg.WCtlVolCfg.Ena", 44043, 2, U32, TAGLIST, READ_WRITE, "-", "P(U), activation", "P(U), Aktivierung"),
    INVERTER_WMODCFG_WCTLVOLCFG_VREFMOD("Inverter.WModCfg.WCtlVolCfg.VRefMod", 44045, 2, U32, TAGLIST, READ_WRITE, "-", "P(U), type of reference voltage", "P(U), Art der Bezugsspannung"),
    INVERTER_WMODCFG_WCTLVOLCFG_WREFMOD("Inverter.WModCfg.WCtlVolCfg.WRefMod", 44047, 2, U32, TAGLIST, READ_WRITE, "-", "P(U), type of reference active power", "P(U), Art der Bezugswirkleistung"),
    INVERTER_WMODCFG_WCTLVOLCFG_CRV_NUMPT("Inverter.WModCfg.WCtlVolCfg.Crv.NumPt", 44049, 2, U32, FIX0, READ_WRITE, "-", "P(U), number of points used", "P(U), Anzahl verwendeter Punkte"),
    INVERTER_WMODCFG_WCTLVOLCFG_CRV_XVAL_1("Inverter.WModCfg.WCtlVolCfg.Crv.XVal", 44051, 2, U32, FIX3, READ_WRITE, "p.u.", "P(U), voltage value", "P(U), Spannungswert"),
    INVERTER_WMODCFG_WCTLVOLCFG_CRV_XVAL_2("Inverter.WModCfg.WCtlVolCfg.Crv.XVal", 44053, 2, U32, FIX3, READ_WRITE, "p.u.", "P(U), voltage value", "P(U), Spannungswert"),
    INVERTER_WMODCFG_WCTLVOLCFG_CRV_XVAL_3("Inverter.WModCfg.WCtlVolCfg.Crv.XVal", 44055, 2, U32, FIX3, READ_WRITE, "p.u.", "P(U), voltage value", "P(U), Spannungswert"),
    INVERTER_WMODCFG_WCTLVOLCFG_CRV_XVAL_4("Inverter.WModCfg.WCtlVolCfg.Crv.XVal", 44057, 2, U32, FIX3, READ_WRITE, "p.u.", "P(U), voltage value", "P(U), Spannungswert"),
    INVERTER_WMODCFG_WCTLVOLCFG_CRV_XVAL_5("Inverter.WModCfg.WCtlVolCfg.Crv.XVal", 44059, 2, U32, FIX3, READ_WRITE, "p.u.", "P(U), voltage value", "P(U), Spannungswert"),
    INVERTER_WMODCFG_WCTLVOLCFG_CRV_XVAL_6("Inverter.WModCfg.WCtlVolCfg.Crv.XVal", 44061, 2, U32, FIX3, READ_WRITE, "p.u.", "P(U), voltage value", "P(U), Spannungswert"),
    INVERTER_WMODCFG_WCTLVOLCFG_CRV_XVAL_7("Inverter.WModCfg.WCtlVolCfg.Crv.XVal", 44063, 2, U32, FIX3, READ_WRITE, "p.u.", "P(U), voltage value", "P(U), Spannungswert"),
    INVERTER_WMODCFG_WCTLVOLCFG_CRV_XVAL_8("Inverter.WModCfg.WCtlVolCfg.Crv.XVal", 44065, 2, U32, FIX3, READ_WRITE, "p.u.", "P(U), voltage value", "P(U), Spannungswert"),
    INVERTER_WMODCFG_WCTLVOLCFG_CRV_YVAL_1("Inverter.WModCfg.WCtlVolCfg.Crv.YVal", 44067, 2, S32, FIX3, READ_WRITE, "%", "P(U), active power value", "P(U), Wirkleistungswert"),
    INVERTER_WMODCFG_WCTLVOLCFG_CRV_YVAL_2("Inverter.WModCfg.WCtlVolCfg.Crv.YVal", 44069, 2, S32, FIX3, READ_WRITE, "%", "P(U), active power value", "P(U), Wirkleistungswert"),
    INVERTER_WMODCFG_WCTLVOLCFG_CRV_YVAL_3("Inverter.WModCfg.WCtlVolCfg.Crv.YVal", 44071, 2, S32, FIX3, READ_WRITE, "%", "P(U), active power value", "P(U), Wirkleistungswert"),
    INVERTER_WMODCFG_WCTLVOLCFG_CRV_YVAL_4("Inverter.WModCfg.WCtlVolCfg.Crv.YVal", 44073, 2, S32, FIX3, READ_WRITE, "%", "P(U), active power value", "P(U), Wirkleistungswert"),
    INVERTER_WMODCFG_WCTLVOLCFG_CRV_YVAL_5("Inverter.WModCfg.WCtlVolCfg.Crv.YVal", 44075, 2, S32, FIX3, READ_WRITE, "%", "P(U), active power value", "P(U), Wirkleistungswert"),
    INVERTER_WMODCFG_WCTLVOLCFG_CRV_YVAL_6("Inverter.WModCfg.WCtlVolCfg.Crv.YVal", 44077, 2, S32, FIX3, READ_WRITE, "%", "P(U), active power value", "P(U), Wirkleistungswert"),
    INVERTER_WMODCFG_WCTLVOLCFG_CRV_YVAL_7("Inverter.WModCfg.WCtlVolCfg.Crv.YVal", 44079, 2, S32, FIX3, READ_WRITE, "%", "P(U), active power value", "P(U), Wirkleistungswert"),
    INVERTER_WMODCFG_WCTLVOLCFG_CRV_YVAL_8("Inverter.WModCfg.WCtlVolCfg.Crv.YVal", 44081, 2, S32, FIX3, READ_WRITE, "%", "P(U), active power value", "P(U), Wirkleistungswert"),
    INVERTER_WMODCFG_WCTLVOLCFG_WTMENA("Inverter.WModCfg.WCtlVolCfg.WTmEna", 44083, 2, U32, TAGLIST, READ_WRITE, "-", "P(U), nominal value filter", "P(U), Sollwertfilter"),
    INVERTER_WMODCFG_WCTLVOLCFG_WTMS("Inverter.WModCfg.WCtlVolCfg.WTms", 44085, 2, U32, FIX2, READ_WRITE, "s", "P(U), setting time, nominal value filter", "P(U), Einstellzeit Sollwertfilter"),
    INVERTER_WMODCFG_WCTLVOLCFG_WGRAENA("Inverter.WModCfg.WCtlVolCfg.WGraEna", 44087, 2, U32, TAGLIST, READ_WRITE, "-", "P(U), limitation of change rate", "P(U), Begrenzung der Änderungsrate"),
    INVERTER_WMODCFG_WCTLVOLCFG_WGRAPOS("Inverter.WModCfg.WCtlVolCfg.WGraPos", 44089, 2, U32, FIX2, READ_WRITE, "%/s", "P(U), increase rate", "P(U), Anstiegsrate"),
    INVERTER_WMODCFG_WCTLVOLCFG_WGRANEG("Inverter.WModCfg.WCtlVolCfg.WGraNeg", 44091, 2, U32, FIX2, READ_WRITE, "%/s", "P(U), decrease rate", "P(U), Absenkungsrate"),
    INVERTER_WMODCFG_WCTLVOLCFG_ACTTMS("Inverter.WModCfg.WCtlVolCfg.ActTms", 44093, 2, U32, DURATION, READ_WRITE, "s", "P(U), tripping delay", "P(U), Auslöseverzögerung"),
    INVERTER_VARMODCFG_VARNOMREFMOD("Inverter.VArModCfg.VArNomRefMod", 44095, 2, U32, TAGLIST, READ_WRITE, "-", "Reactive power mode, reference size for reactive power setting", "Blindleistungsverfahren, Bezugsgröße für Blindleistungsvorgaben"),
    INVERTER_VARMODCFG_VARMODOUT("Inverter.VArModCfg.VArModOut", 41319, 2, U32, TAGLIST, READ_WRITE, "-", "Reactive power mode in case of active power output", "Blindleistungsverfahren bei Wirkleistungsabgabe"),
    INVERTER_VARMODCFG_VARMODIN("Inverter.VArModCfg.VArModIn", 41321, 2, U32, TAGLIST, READ_WRITE, "-", "Reactive power mode in case of active power draw", "Blindleistungsverfahren bei Wirkleistungsaufnahme"),
    INVERTER_VARMODCFG_VARMODZERW("Inverter.VArModCfg.VArModZerW", 41323, 2, U32, TAGLIST, READ_WRITE, "-", "Reactive power for zero active power", "Blindleistungsverfahren bei Nullwirkleistung"),
    INVERTER_VARMODCFG_PFMINENA("Inverter.VArModCfg.PFMinEna", 41367, 2, U32, TAGLIST, READ_WRITE, "-", "Nom-cos ? PFMinQ1-Q4", "Nenn-cos ? PFMinQ1-Q4"),
    INVERTER_VARMODCFG_OUTWNOMLIMACT("Inverter.VArModCfg.OutWNomLimAct", 41369, 2, S32, FIX3, READ_WRITE, "%", "Activation threshold for active power output", "Aktivierungsschwelle bei Wirkleistungsabgabe"),
    INVERTER_VARMODCFG_OUTWNOMLIMDEACT("Inverter.VArModCfg.OutWNomLimDeAct", 41371, 2, S32, FIX3, READ_WRITE, "%", "Deactivation threshold for active power output", "Deaktivierungsschwelle bei Wirkleistungsabgabe"),
    INVERTER_VARMODCFG_INWNOMLIMACT("Inverter.VArModCfg.InWNomLimAct", 41373, 2, S32, FIX3, READ_WRITE, "%", "Activation threshold for active power draw", "Aktivierungsschwelle bei Wirkleistungsaufnahme"),
    INVERTER_VARMODCFG_INWNOMLIMDEACT("Inverter.VArModCfg.InWNomLimDeAct", 41375, 2, S32, FIX3, READ_WRITE, "%", "Deactivation threshold for active power draw", "Deaktivierungsschwelle bei Wirkleistungsaufnahme"),
    INVERTER_VARMODCFG_VARCFG_DYN_VARTMENA("Inverter.VArModCfg.VArCfg.Dyn.VArTmEna", 44101, 2, U32, TAGLIST, READ_WRITE, "-", "Reactive power setting, nominal value filter", "Blindleistungsvorgabe, Sollwertfilter"),
    INVERTER_VARMODCFG_VARCFG_DYN_VARTMS("Inverter.VArModCfg.VArCfg.Dyn.VArTms", 44103, 2, U32, FIX2, READ_WRITE, "s", "Reactive power setting, setting time, nominal value filter", "Blindleistungsvorgabe, Einstellzeit Sollwertfilter"),
    INVERTER_VARMODCFG_VARCFG_DYN_VARGRAENA("Inverter.VArModCfg.VArCfg.Dyn.VArGraEna", 44105, 2, U32, TAGLIST, READ_WRITE, "-", "Reactive power setting, limitation of change rate", "Blindleistungsvorgabe, Begrenzung der Änderungsrate"),
    INVERTER_VARMODCFG_VARCFG_DYN_VARGRAPOS("Inverter.VArModCfg.VArCfg.Dyn.VArGraPos", 44107, 2, U32, FIX2, READ_WRITE, "%/s", "Reactive power setting, increase rate", "Blindleistungsvorgabe, Anstiegsrate"),
    INVERTER_VARMODCFG_VARCFG_DYN_VARGRANEG("Inverter.VArModCfg.VArCfg.Dyn.VArGraNeg", 44109, 2, U32, FIX2, READ_WRITE, "%/s", "Reactive power setting, decrease rate", "Blindleistungsvorgabe, Absenkungsrate"),
    INVERTER_VARMODCFG_PFCNSTCFG_PFIN("Inverter.VArModCfg.PFCnstCfg.PFIn", 44119, 2, U32, FIX4, READ_WRITE, "-", "Manual cos ? setting, cos ? nominal value in case of active power draw", "Manuelle cos ?-Vorgabe, cos ?-Sollwert bei Wirkleistungsbezug"),
    INVERTER_VARMODCFG_PFCNSTCFG_PFEXTIN("Inverter.VArModCfg.PFCnstCfg.PFExtIn", 44121, 2, U32, TAGLIST, READ_WRITE, "-", "Manual cos ? setting, excitation type in case of active power draw", "Manuelle cos ?-Vorgabe, Erregungsart bei Wirkleistungsbezug"),
    INVERTER_VARMODCFG_PFCFG_DYN_WFILTMENA("Inverter.VArModCfg.PFCfg.Dyn.WFilTmEna", 44123, 2, U32, TAGLIST, READ_WRITE, "-", "cos ? setting, actual value filter for active power value", "cos ?-Vorgabe, Istwertfilter für Wirkleistungsmesswert"),
    INVERTER_VARMODCFG_PFCFG_DYN_WFILTMS("Inverter.VArModCfg.PFCfg.Dyn.WFilTms", 44125, 2, U32, FIX2, READ_WRITE, "s", "cos ? setting, actual value filter for active power value", "cos ?-Vorgabe, Istwertfilter für Wirkleistungsmesswert"),
    INVERTER_VARMODCFG_PFCFG_DYN_VARTMENA("Inverter.VArModCfg.PFCfg.Dyn.VArTmEna", 44127, 2, U32, TAGLIST, READ_WRITE, "-", "cos ? setting, nominal value filter", "cos ?-Vorgabe, Sollwertfilter"),
    INVERTER_VARMODCFG_PFCFG_DYN_VARTMS("Inverter.VArModCfg.PFCfg.Dyn.VArTms", 44129, 2, U32, FIX2, READ_WRITE, "s", "cos ? setting, setting time, nominal value filter", "cos ?-Vorgabe, Einstellzeit Sollwertfilter"),
    INVERTER_VARMODCFG_PFCFG_DYN_VARGRAENA("Inverter.VArModCfg.PFCfg.Dyn.VArGraEna", 44131, 2, U32, TAGLIST, READ_WRITE, "-", "cos ? setting, limitation of change rate", "cos ?-Vorgabe, Begrenzung der Änderungsrate"),
    INVERTER_VARMODCFG_PFCFG_DYN_VARGRAPOS("Inverter.VArModCfg.PFCfg.Dyn.VArGraPos", 44133, 2, U32, FIX2, READ_WRITE, "%/s", "cos ? setting, increase rate", "cos ?-Vorgabe, Anstiegsrate"),
    INVERTER_VARMODCFG_PFCFG_DYN_VARGRANEG("Inverter.VArModCfg.PFCfg.Dyn.VArGraNeg", 44135, 2, U32, FIX2, READ_WRITE, "%/s", "cos ? setting, decrease rate", "cos ?-Vorgabe, Absenkungsrate"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_CRV_NUMPT("Inverter.VArModCfg.VArCtlVolCfg.Crv.NumPt", 44191, 2, U32, FIX0, READ_WRITE, "-", "Q(V), number of support points to be used", "Q(U), Anzahl verwendeter Stützpunkte"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_CRV_XVAL_1("Inverter.VArModCfg.VArCtlVolCfg.Crv.XVal", 41303, 2, U32, FIX3, READ_WRITE, "p.u.", "Q(V), voltage value", "Q(U), Spannungswert"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_CRV_XVAL_2("Inverter.VArModCfg.VArCtlVolCfg.Crv.XVal", 41325, 2, U32, FIX3, READ_WRITE, "p.u.", "Q(V), voltage value", "Q(U), Spannungswert"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_CRV_XVAL_3("Inverter.VArModCfg.VArCtlVolCfg.Crv.XVal", 41327, 2, U32, FIX3, READ_WRITE, "p.u.", "Q(V), voltage value", "Q(U), Spannungswert"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_CRV_XVAL_4("Inverter.VArModCfg.VArCtlVolCfg.Crv.XVal", 41329, 2, U32, FIX3, READ_WRITE, "p.u.", "Q(V), voltage value", "Q(U), Spannungswert"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_CRV_XVAL_5("Inverter.VArModCfg.VArCtlVolCfg.Crv.XVal", 41331, 2, U32, FIX3, READ_WRITE, "p.u.", "Q(V), voltage value", "Q(U), Spannungswert"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_CRV_XVAL_6("Inverter.VArModCfg.VArCtlVolCfg.Crv.XVal", 41333, 2, U32, FIX3, READ_WRITE, "p.u.", "Q(V), voltage value", "Q(U), Spannungswert"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_CRV_XVAL_7("Inverter.VArModCfg.VArCtlVolCfg.Crv.XVal", 41335, 2, U32, FIX3, READ_WRITE, "p.u.", "Q(V), voltage value", "Q(U), Spannungswert"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_CRV_XVAL_8("Inverter.VArModCfg.VArCtlVolCfg.Crv.XVal", 41337, 2, U32, FIX3, READ_WRITE, "p.u.", "Q(V), voltage value", "Q(U), Spannungswert"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_CRV_XVAL_9("Inverter.VArModCfg.VArCtlVolCfg.Crv.XVal", 41339, 2, U32, FIX3, READ_WRITE, "p.u.", "Q(V), voltage value", "Q(U), Spannungswert"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_CRV_YVAL_1("Inverter.VArModCfg.VArCtlVolCfg.Crv.YVal", 41305, 2, S32, FIX3, READ_WRITE, "%", "Q(V), reactive power value", "Q(U), Blindleistungswert"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_CRV_YVAL_2("Inverter.VArModCfg.VArCtlVolCfg.Crv.YVal", 41341, 2, S32, FIX3, READ_WRITE, "%", "Q(V), reactive power value", "Q(U), Blindleistungswert"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_CRV_YVAL_3("Inverter.VArModCfg.VArCtlVolCfg.Crv.YVal", 41343, 2, S32, FIX3, READ_WRITE, "%", "Q(V), reactive power value", "Q(U), Blindleistungswert"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_CRV_YVAL_4("Inverter.VArModCfg.VArCtlVolCfg.Crv.YVal", 41345, 2, S32, FIX3, READ_WRITE, "%", "Q(V), reactive power value", "Q(U), Blindleistungswert"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_CRV_YVAL_5("Inverter.VArModCfg.VArCtlVolCfg.Crv.YVal", 41347, 2, S32, FIX3, READ_WRITE, "%", "Q(V), reactive power value", "Q(U), Blindleistungswert"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_CRV_YVAL_6("Inverter.VArModCfg.VArCtlVolCfg.Crv.YVal", 41349, 2, S32, FIX3, READ_WRITE, "%", "Q(V), reactive power value", "Q(U), Blindleistungswert"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_CRV_YVAL_7("Inverter.VArModCfg.VArCtlVolCfg.Crv.YVal", 41351, 2, S32, FIX3, READ_WRITE, "%", "Q(V), reactive power value", "Q(U), Blindleistungswert"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_CRV_YVAL_8("Inverter.VArModCfg.VArCtlVolCfg.Crv.YVal", 41353, 2, S32, FIX3, READ_WRITE, "%", "Q(V), reactive power value", "Q(U), Blindleistungswert"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_CRV_YVAL_9("Inverter.VArModCfg.VArCtlVolCfg.Crv.YVal", 41355, 2, S32, FIX3, READ_WRITE, "%", "Q(V), reactive power value", "Q(U), Blindleistungswert"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_DYN_VARTMENA("Inverter.VArModCfg.VArCtlVolCfg.Dyn.VArTmEna", 44199, 2, U32, TAGLIST, READ_WRITE, "-", "Q(V), nominal value filter", "Q(U), Sollwertfilter"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_DYN_VARTMS("Inverter.VArModCfg.VArCtlVolCfg.Dyn.VArTms", 44201, 2, U32, FIX0, READ_WRITE, "s", "Q(V), setting time, nominal value filter", "Q(U), Einstellzeit Sollwertfilter"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_DYN_VARGRAENA("Inverter.VArModCfg.VArCtlVolCfg.Dyn.VArGraEna", 44203, 2, U32, TAGLIST, READ_WRITE, "-", "Q(V), limitation of change rate", "Q(U), Begrenzung der Änderungsrate"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_DYN_VARGRAPOS("Inverter.VArModCfg.VArCtlVolCfg.Dyn.VArGraPos", 44205, 2, U32, FIX2, READ_WRITE, "%/s", "Q(V), increase rate", "Q(U), Anstiegsrate"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_DYN_VARGRANEG("Inverter.VArModCfg.VArCtlVolCfg.Dyn.VArGraNeg", 44207, 2, U32, FIX2, READ_WRITE, "%/s", "Q(V), decrease rate", "Q(U), Absenkungsrate"),
    INVERTER_VARMODCFG_VARCTLVOLCFG_DYN_ACTTMS("Inverter.VArModCfg.VArCtlVolCfg.Dyn.ActTms", 44209, 2, U32, FIX0, READ_WRITE, "s", "Q(V), tripping delay", "Q(U), Auslöseverzögerung"),
    INVERTER_VARMODCFG_PFCTLWCFG_CRV_NUMPT("Inverter.VArModCfg.PFCtlWCfg.Crv.NumPt", 44213, 2, U32, FIX0, READ_WRITE, "-", "cos ?(P), number of support points to be used", "cos ?(P), Anzahl verwendeter Stützpunkte"),
    INVERTER_VARMODCFG_PFCTLWCFG_CRV_PFEXT_1("Inverter.VArModCfg.PFCtlWCfg.Crv.PFExt", 44215, 2, U32, TAGLIST, READ_WRITE, "-", "cos ?(P), excitation type", "cos ?(P), Erregungsart"),
    INVERTER_VARMODCFG_PFCTLWCFG_CRV_PFEXT_2("Inverter.VArModCfg.PFCtlWCfg.Crv.PFExt", 44217, 2, U32, TAGLIST, READ_WRITE, "-", "cos ?(P), excitation type", "cos ?(P), Erregungsart"),
    INVERTER_VARMODCFG_PFCTLWCFG_CRV_PFEXT_3("Inverter.VArModCfg.PFCtlWCfg.Crv.PFExt", 44219, 2, U32, TAGLIST, READ_WRITE, "-", "cos ?(P), excitation type", "cos ?(P), Erregungsart"),
    INVERTER_VARMODCFG_PFCTLWCFG_CRV_PFEXT_4("Inverter.VArModCfg.PFCtlWCfg.Crv.PFExt", 44221, 2, U32, TAGLIST, READ_WRITE, "-", "cos ?(P), excitation type", "cos ?(P), Erregungsart"),
    INVERTER_VARMODCFG_PFCTLWCFG_CRV_PF_1("Inverter.VArModCfg.PFCtlWCfg.Crv.PF", 44223, 2, U32, FIX4, READ_WRITE, "-", "cos ?(P), cos ? nominal value", "cos ?(P), cos ?-Sollwert"),
    INVERTER_VARMODCFG_PFCTLWCFG_CRV_PF_2("Inverter.VArModCfg.PFCtlWCfg.Crv.PF", 44225, 2, U32, FIX4, READ_WRITE, "-", "cos ?(P), cos ? nominal value", "cos ?(P), cos ?-Sollwert"),
    INVERTER_VARMODCFG_PFCTLWCFG_CRV_PF_3("Inverter.VArModCfg.PFCtlWCfg.Crv.PF", 44227, 2, U32, FIX4, READ_WRITE, "-", "cos ?(P), cos ? nominal value", "cos ?(P), cos ?-Sollwert"),
    INVERTER_VARMODCFG_PFCTLWCFG_CRV_PF_4("Inverter.VArModCfg.PFCtlWCfg.Crv.PF", 44229, 2, U32, FIX4, READ_WRITE, "-", "cos ?(P), cos ? nominal value", "cos ?(P), cos ?-Sollwert"),
    INVERTER_VARMODCFG_PFCTLWCFG_CRV_WNOM_1("Inverter.VArModCfg.PFCtlWCfg.Crv.WNom", 44231, 2, S32, FIX0, READ_WRITE, "%", "cos ?(P), active power", "cos ?(P), Wirkleistung"),
    INVERTER_VARMODCFG_PFCTLWCFG_CRV_WNOM_2("Inverter.VArModCfg.PFCtlWCfg.Crv.WNom", 44233, 2, S32, FIX0, READ_WRITE, "%", "cos ?(P), active power", "cos ?(P), Wirkleistung"),
    INVERTER_VARMODCFG_PFCTLWCFG_CRV_WNOM_3("Inverter.VArModCfg.PFCtlWCfg.Crv.WNom", 44235, 2, S32, FIX0, READ_WRITE, "%", "cos ?(P), active power", "cos ?(P), Wirkleistung"),
    INVERTER_VARMODCFG_PFCTLWCFG_CRV_WNOM_4("Inverter.VArModCfg.PFCtlWCfg.Crv.WNom", 44237, 2, S32, FIX0, READ_WRITE, "%", "cos ?(P), active power", "cos ?(P), Wirkleistung"),
    INVERTER_VARMODCFG_PFCTLWCFG_DYN_WFILTMENA("Inverter.VArModCfg.PFCtlWCfg.Dyn.WFilTmEna", 44239, 2, U32, TAGLIST, READ_WRITE, "-", "cos ?(P), actual value filter for active power measured value", "cos ?(P), Istwertfilter für Wirkleistungsmesswert"),
    INVERTER_VARMODCFG_PFCTLWCFG_DYN_WFILTMS("Inverter.VArModCfg.PFCtlWCfg.Dyn.WFilTms", 44241, 2, U32, FIX2, READ_WRITE, "s", "cos ?(P), setting time, actual value filter", "cos ?(P), Einstellzeit Istwertfilter"),
    INVERTER_VARMODCFG_PFCTLWCFG_DYN_VARTMENA("Inverter.VArModCfg.PFCtlWCfg.Dyn.VArTmEna", 44243, 2, U32, TAGLIST, READ_WRITE, "-", "cos ?(P), nominal value filter", "cos ?(P), Sollwertfilter"),
    INVERTER_VARMODCFG_PFCTLWCFG_DYN_VARTMS("Inverter.VArModCfg.PFCtlWCfg.Dyn.VArTms", 44245, 2, U32, FIX2, READ_WRITE, "s", "cos ?(P), setting time, nominal value filter", "cos ?(P), Einstellzeit Sollwertfilter"),
    INVERTER_VARMODCFG_PFCTLWCFG_DYN_VARGRAENA("Inverter.VArModCfg.PFCtlWCfg.Dyn.VArGraEna", 44247, 2, U32, TAGLIST, READ_WRITE, "-", "cos ?(P), limitation of change rate", "cos ?(P), Begrenzung der Änderungsrate"),
    INVERTER_VARMODCFG_PFCTLWCFG_DYN_VARGRAPOS("Inverter.VArModCfg.PFCtlWCfg.Dyn.VArGraPos", 44249, 2, U32, FIX2, READ_WRITE, "%/s", "cos ?(P), increase rate", "cos ?(P), Anstiegsrate"),
    INVERTER_VARMODCFG_PFCTLWCFG_DYN_VARGRANEG("Inverter.VArModCfg.PFCtlWCfg.Dyn.VArGraNeg", 44251, 2, U32, FIX2, READ_WRITE, "%/s", "cos ?(P), decrease rate", "cos ?(P), Absenkungsrate"),
    INVERTER_VARMODCFG_PFCTLWCFG_DYN_ACTTMS("Inverter.VArModCfg.PFCtlWCfg.Dyn.ActTms", 44253, 2, U32, FIX0, READ_WRITE, "s", "cos ?(P), tripping delay", "cos ?(P), Auslöseverzögerung"),
    GRIDGUARD_CNTRY_VOLCTL_MAXPU("GridGuard.Cntry.VolCtl.MaxPu", 44297, 2, U32, FIX3, READ_WRITE, "p.u.", "Voltage monitoring, upper maximum threshold", "Spannungsüberwachung, obere Maximalschwelle"),
    GRIDGUARD_CNTRY_VOLCTL_MAXPUTMMS("GridGuard.Cntry.VolCtl.MaxPuTmms", 44303, 2, U32, FIX0, READ_WRITE, "ms", "Voltage monitoring, upper max. threshold trip. time", "Spannungsüberwachung, obere Maximalschwelle Auslösezeit"),
    GRIDGUARD_CNTRY_VOLCTL_HHLIMPU("GridGuard.Cntry.VolCtl.hhLimPu", 44299, 2, U32, FIX3, READ_WRITE, "p.u.", "Voltage monitoring, median maximum threshold", "Spannungsüberwachung, mittlere Maximalschwelle"),
    GRIDGUARD_CNTRY_VOLCTL_HHLIMTMMS("GridGuard.Cntry.VolCtl.hhLimTmms", 40450, 2, U32, FIX0, READ_WRITE, "ms", "Voltage monitoring, median max. threshold trip.time", "Spannungsüberwachung, mittlere Maximalschwelle Auslösezeit"),
    GRIDGUARD_CNTRY_VOLCTL_HLIMPU("GridGuard.Cntry.VolCtl.hLimPu", 44301, 2, U32, FIX3, READ_WRITE, "p.u.", "Voltage monitoring, lower maximum threshold", "Spannungsüberwachung, untere Maximalschwelle"),
    GRIDGUARD_CNTRY_VOLCTL_HLIMTMMS("GridGuard.Cntry.VolCtl.hLimTmms", 40456, 2, U32, FIX0, READ_WRITE, "ms", "Voltage monitoring, lower max. threshold trip. time", "Spannungsüberwachung, untere Maximalschwelle Auslösezeit"),
    GRIDGUARD_CNTRY_VOLCTL_LLIMPU("GridGuard.Cntry.VolCtl.lLimPu", 44309, 2, U32, FIX3, READ_WRITE, "p.u.", "Voltage monitoring, upper minimum threshold", "Spannungsüberwachung, obere Minimalschwelle"),
    GRIDGUARD_CNTRY_VOLCTL_LLIMTMMS("GridGuard.Cntry.VolCtl.lLimTmms", 40462, 2, U32, FIX0, READ_WRITE, "ms", "Voltage monitoring, lower min. threshold trip. time", "Spannungsüberwachung, obere Minimalschwelle Auslösezeit"),
    GRIDGUARD_CNTRY_VOLCTL_LLLIMPU("GridGuard.Cntry.VolCtl.llLimPu", 44307, 2, U32, FIX3, READ_WRITE, "p.u.", "Voltage monitoring, median minimum threshold", "Spannungsüberwachung, mittlere Minimalschwelle"),
    GRIDGUARD_CNTRY_VOLCTL_LLLIMTMMS("GridGuard.Cntry.VolCtl.llLimTmms", 40466, 2, U32, FIX0, READ_WRITE, "ms", "Voltage monitoring, median min. threshold trip.time", "Spannungsüberwachung, mittlere Minimalschwelle Auslösezeit"),
    GRIDGUARD_CNTRY_VOLCTL_MINPU("GridGuard.Cntry.VolCtl.MinPu", 44305, 2, U32, FIX3, READ_WRITE, "p.u.", "Voltage monitoring, lower minimum threshold", "Spannungsüberwachung, untere Minimalschwelle"),
    GRIDGUARD_CNTRY_VOLCTL_MINTMMS("GridGuard.Cntry.VolCtl.MinTmms", 40468, 2, U32, FIX0, READ_WRITE, "ms", "Voltage monitoring, lower min. threshold trip. time", "Spannungsüberwachung, untere Minimalschwelle Auslösezeit"),
    GRIDGUARD_CNTRY_VOLCTL_RECONMAXPU("GridGuard.Cntry.VolCtl.ReconMaxPu", 44311, 2, U32, FIX3, READ_WRITE, "p.u.", "Max. voltage for reconnection", "Maximale Zuschaltspannung"),
    GRIDGUARD_CNTRY_VOLCTL_RECONMINPU("GridGuard.Cntry.VolCtl.ReconMinPu", 44313, 2, U32, FIX3, READ_WRITE, "p.u.", "Min. voltage for reconnection", "Minimale Zuschaltspannung"),
    GRIDGUARD_CNTRY_VOLCTL_MAXPEAKPU("GridGuard.Cntry.VolCtl.MaxPeakPu", 44315, 2, U32, FIX3, READ_WRITE, "p.u.", "Voltage monitoring, peak voltage threshold", "Spannungsüberwachung, Spitzenspannungsschwelle"),
    GRIDGUARD_CNTRY_VOLCTL_MAXPEAKTMMS("GridGuard.Cntry.VolCtl.MaxPeakTmms", 44317, 2, U32, FIX3, READ_WRITE, "ms", "Voltage monitoring, peak voltage threshold tripping time", "Spannungsüberwachung,  Spitzenspannungsschwelle Auslösezeit"),
    INVERTER_DGSMODCFG_DGSMOD("Inverter.DGSModCfg.DGSMod", 40250, 2, U32, TAGLIST, READ_WRITE, "-", "Dynamic grid support, operating mode", "Dynamische Netzstützung, Betriebsart"),
    INVERTER_DGSMODCFG_ZERCUROVVOLPU("Inverter.DGSModCfg.ZerCurOvVolPu", 44319, 2, U32, FIX2, READ_WRITE, "p.u.", "Dynamic grid support, overvoltage threshold for zero current", "Dynamische Netzstützung, Überspannungsschwelle für Nullstrom"),
    INVERTER_DGSMODCFG_ZERCURUNVOLPU("Inverter.DGSModCfg.ZerCurUnVolPu", 44321, 2, U32, FIX2, READ_WRITE, "p.u.", "Dynamic grid support, undervoltage threshold for zero current", "Dynamische Netzstützung, Unterspannungsschwelle für Nullstrom"),
    INVERTER_DGSMODCFG_HYSTVOLPU("Inverter.DGSModCfg.HystVolPu", 44323, 2, U32, FIX2, READ_WRITE, "p.u.", "Dynamic grid support, hysteresis voltage", "Dynamische Netzstützung, Hysteresespannung"),
    GRIDGUARD_CNTRY_FRQCTL_MAX("GridGuard.Cntry.FrqCtl.Max", 40103, 2, U32, FIX2, READ_WRITE, "Hz", "Frequency monitoring upper maximum threshold", "Frequenzüberwachung, obere Maximalschwelle"),
    GRIDGUARD_CNTRY_FRQCTL_MAXTMMS("GridGuard.Cntry.FrqCtl.MaxTmms", 40426, 2, U32, FIX0, READ_WRITE, "ms", "Frequency monitoring upper max. threshold trip. time", "Frequenzüberwachung, obere Maximalschwelle Auslösezeit"),
    GRIDGUARD_CNTRY_FRQCTL_HLIM("GridGuard.Cntry.FrqCtl.hLim", 40432, 2, U32, FIX2, READ_WRITE, "Hz", "Frequency monitoring, lower maximum threshold", "Frequenzüberwachung, untere Maximalschwelle"),
    GRIDGUARD_CNTRY_FRQCTL_HLIMTMMS("GridGuard.Cntry.FrqCtl.hLimTmms", 40434, 2, U32, FIX0, READ_WRITE, "ms", "Frequency monitoring, lower max. threshold trip. time", "Frequenzüberwachung, untere Maximalschwelle Auslösezeit"),
    GRIDGUARD_CNTRY_FRQCTL_LLIM("GridGuard.Cntry.FrqCtl.lLim", 40436, 2, U32, FIX2, READ_WRITE, "Hz", "Frequency monitoring upper minimum threshold", "Frequenzüberwachung, obere Minimalschwelle"),
    GRIDGUARD_CNTRY_FRQCTL_LLIMTMMS("GridGuard.Cntry.FrqCtl.lLimTmms", 40438, 2, U32, FIX0, READ_WRITE, "ms", "Frequency monitoring, upper min. threshold trip. time", "Frequenzüberwachung, obere Minimalschwelle Auslösezeit"),
    GRIDGUARD_CNTRY_FRQCTL_MIN("GridGuard.Cntry.FrqCtl.Min", 40101, 2, U32, FIX2, READ_WRITE, "Hz", "Frequency monitoring, lower minimum threshold", "Frequenzüberwachung, untere Minimalschwelle"),
    GRIDGUARD_CNTRY_FRQCTL_MINTMMS("GridGuard.Cntry.FrqCtl.MinTmms", 40444, 2, U32, FIX0, READ_WRITE, "ms", "Frequency monitoring, lower min. threshold trip. time", "Frequenzüberwachung, untere Minimalschwelle Auslösezeit"),
    GRIDGUARD_CNTRY_FRQCTL_RECONMIN("GridGuard.Cntry.FrqCtl.ReconMin", 41127, 2, U32, FIX2, READ_WRITE, "Hz", "Minimum switching frequency", "Minimale Zuschaltfrequenz"),
    GRIDGUARD_CNTRY_FRQCTL_RECONMAX("GridGuard.Cntry.FrqCtl.ReconMax", 41129, 2, U32, FIX2, READ_WRITE, "Hz", "Maximum switching frequency", "Maximale Zuschaltfrequenz"),
    INVERTER_WCTLHZMODCFG_ENA("Inverter.WCtlHzModCfg.Ena", 44333, 2, U32, TAGLIST, READ_WRITE, "-", "P(f) characteristic curve", "P(f)-Kennlinie"),
    INVERTER_WCTLHZMODCFG_REFMODOV("Inverter.WCtlHzModCfg.RefModOv", 44335, 2, U32, TAGLIST, READ_WRITE, "-", "P(f) reference value for active power in case of overfrequency", "P(f), Bezugsgröße für Wirkleistung bei Überfrequenz"),
    INVERTER_WCTLHZMODCFG_REFMODUN("Inverter.WCtlHzModCfg.RefModUn", 44337, 2, U32, TAGLIST, READ_WRITE, "-", "P(f), reference value for active power for underfrequency", "P(f), Bezugsgröße für Wirkleistung bei Unterfrequenz"),
    INVERTER_WCTLHZMODCFG_WTMS("Inverter.WCtlHzModCfg.WTms", 44339, 2, U32, FIX2, READ_WRITE, "s", "P(f), setting time", "P(f), Einstellzeit"),
    INVERTER_WCTLHZMODCFG_WCTLHZCFG_HYSTENAOV("Inverter.WCtlHzModCfg.WCtlHzCfg.HystEnaOv", 44341, 2, U32, TAGLIST, READ_WRITE, "-", "P(f), hysteresis in case of overfrequency", "P(f), Hysterese bei Überfrequenz"),
    INVERTER_WCTLHZMODCFG_WCTLHZCFG_HYSTENAUN("Inverter.WCtlHzModCfg.WCtlHzCfg.HystEnaUn", 44343, 2, U32, TAGLIST, READ_WRITE, "-", "P(f), hysteresis in case of underfrequency", "P(f), Hysterese bei Unterfrequenz"),
    INVERTER_WCTLHZMODCFG_WCTLHZCFG_HZUNSTOP("Inverter.WCtlHzModCfg.WCtlHzCfg.HzUnStop", 44371, 2, S32, FIX3, READ_WRITE, "Hz", "P(f), reset underfrequency", "P(f), Rücksetzunterfrequenz"),
    INVERTER_WCTLHZMODCFG_WCTLHZCFG_HZUN_1("Inverter.WCtlHzModCfg.WCtlHzCfg.HzUn", 44359, 2, S32, FIX3, READ_WRITE, "Hz", "P(f), buckling underfrequency", "P(f), Knickunterfrequenz"),
    INVERTER_WCTLHZMODCFG_WCTLHZCFG_HZUN_2("Inverter.WCtlHzModCfg.WCtlHzCfg.HzUn", 44361, 2, S32, FIX3, READ_WRITE, "Hz", "P(f), buckling underfrequency", "P(f), Knickunterfrequenz"),
    INVERTER_WCTLHZMODCFG_WCTLHZCFG_HZUN_3("Inverter.WCtlHzModCfg.WCtlHzCfg.HzUn", 44363, 2, S32, FIX3, READ_WRITE, "Hz", "P(f), buckling underfrequency", "P(f), Knickunterfrequenz"),
    INVERTER_WCTLHZMODCFG_WCTLHZCFG_HZUNGRA_1("Inverter.WCtlHzModCfg.WCtlHzCfg.HzUnGra", 44365, 2, S32, FIX3, READ_WRITE, "%", "P(f), active power change per Hz in case of underfrequency", "P(f), Wirkleistungsänderung pro Hz bei Unterfrequenz"),
    INVERTER_WCTLHZMODCFG_WCTLHZCFG_HZUNGRA_2("Inverter.WCtlHzModCfg.WCtlHzCfg.HzUnGra", 44367, 2, S32, FIX3, READ_WRITE, "%", "P(f), active power change per Hz in case of underfrequency", "P(f), Wirkleistungsänderung pro Hz bei Unterfrequenz"),
    INVERTER_WCTLHZMODCFG_WCTLHZCFG_HZUNGRA_3("Inverter.WCtlHzModCfg.WCtlHzCfg.HzUnGra", 44369, 2, S32, FIX3, READ_WRITE, "%", "P(f), active power change per Hz in case of underfrequency", "P(f), Wirkleistungsänderung pro Hz bei Unterfrequenz"),
    INVERTER_WCTLHZMODCFG_WCTLHZCFG_HZOV_1("Inverter.WCtlHzModCfg.WCtlHzCfg.HzOv", 44345, 2, U32, FIX3, READ_WRITE, "Hz", "P(f), buckling overfrequency", "P(f), Knicküberfrequenz"),
    INVERTER_WCTLHZMODCFG_WCTLHZCFG_HZOV_2("Inverter.WCtlHzModCfg.WCtlHzCfg.HzOv", 44347, 2, U32, FIX3, READ_WRITE, "Hz", "P(f), buckling overfrequency", "P(f), Knicküberfrequenz"),
    INVERTER_WCTLHZMODCFG_WCTLHZCFG_HZOV_3("Inverter.WCtlHzModCfg.WCtlHzCfg.HzOv", 44349, 2, U32, FIX3, READ_WRITE, "Hz", "P(f), buckling overfrequency", "P(f), Knicküberfrequenz"),
    INVERTER_WCTLHZMODCFG_WCTLHZCFG_HZOVGRA_1("Inverter.WCtlHzModCfg.WCtlHzCfg.HzOvGra", 44351, 2, S32, FIX3, READ_WRITE, "%", "P(f), active power change per Hz in case of overfrequency", "P(f), Wirkleistungsänderung pro Hz bei Überfrequenz"),
    INVERTER_WCTLHZMODCFG_WCTLHZCFG_HZOVGRA_2("Inverter.WCtlHzModCfg.WCtlHzCfg.HzOvGra", 44353, 2, S32, FIX3, READ_WRITE, "%", "P(f), active power change per Hz in case of overfrequency", "P(f), Wirkleistungsänderung pro Hz bei Überfrequenz"),
    INVERTER_WCTLHZMODCFG_WCTLHZCFG_HZOVGRA_3("Inverter.WCtlHzModCfg.WCtlHzCfg.HzOvGra", 44355, 2, S32, FIX3, READ_WRITE, "%", "P(f), active power change per Hz in case of overfrequency", "P(f), Wirkleistungsänderung pro Hz bei Überfrequenz"),
    INVERTER_WCTLHZMODCFG_WCTLHZCFG_WCTLTMMS("Inverter.WCtlHzModCfg.WCtlHzCfg.WCtlTmms", 44373, 2, U32, FIX0, READ_WRITE, "ms", "P(f), tripping delay", "P(f), Auslöseverzögerung"),
    INVERTER_WCTLHZMODCFG_WCTLHZCFG_HZSTOPWGRATMS("Inverter.WCtlHzModCfg.WCtlHzCfg.HzStopWGraTms", 44375, 2, U32, DURATION, READ_WRITE, "s", "P(f), lag time", "P(f), Nachlaufzeit"),
    INVERTER_WCTLHZMODCFG_WCTLHZCFG_HZSTOPWGRA("Inverter.WCtlHzModCfg.WCtlHzCfg.HzStopWGra", 40242, 2, U32, FIX0, READ_WRITE, "%/min", "P(f), active power change rate after fault end", "P(f), Wirkleistungsänderungsrate nach Fehlerende"),
    GRIDGUARD_CNTRY_AID_HZMON_STT("GridGuard.Cntry.Aid.HzMon.Stt", 44377, 2, U32, TAGLIST, READ_WRITE, "-", "Islanding detection, status of frequency monitor", "Inselnetzerkennung, Status der Frequenzüberwachung"),
    GRIDGUARD_CNTRY_AID_HZMON_HZMONTMMS("GridGuard.Cntry.Aid.HzMon.HzMonTmms", 44379, 2, U32, FIX0, READ_WRITE, "ms", "Islanding detection, tripping time of the frequency monitor", "Inselnetzerkennung, Auslösezeit der Frequenzüberwachung"),
    GRIDGUARD_CNTRY_HZRTG("GridGuard.Cntry.HzRtg", 40135, 2, U32, FIX2, READ_WRITE, "Hz", "Nominal frequency", "Nennfrequenz"),
    CNTRYSETTINGS_LANG("CntrySettings.Lang", 40013, 2, U32, TAGLIST, READ_WRITE, "-", "Language of the user interface", "Sprache der Oberfläche"),
    DTTM_TMZN("DtTm.TmZn", 40003, 2, U32, TAGLIST, READ_WRITE, "-", "Time zone", "Zeitzone"),
    DTTM_DLSVISON("DtTm.DlSvIsOn", 40005, 2, U32, TAGLIST, READ_WRITE, "-", "Standard/Daylight saving time conversion on", "Automatische Sommer-/Winterzeitumstellung eingeschaltet"),
    METERING_DYWHOUT_WH_64("Metering.DyWhOut", 30517, 4, U64, FIX0, READ_ONLY, "Wh", "Daily yield", "Tagesertrag"),
    METERING_DYWHOUT_WH_32("Metering.DyWhOut", 30535, 2, U32, FIX0, READ_ONLY, "Wh", "Daily yield", "Tagesertrag"),
    METERING_DYWHOUT_KWH("Metering.DyWhOut", 30537, 2, U32, FIX0, READ_ONLY, "kWh", "Daily yield", "Tagesertrag"),
    METERING_DYWHOUT_MWH("Metering.DyWhOut", 30539, 2, U32, FIX0, READ_ONLY, "MWh", "Daily yield", "Tagesertrag"),
    METERING_GRIDMS_TOTWOUT("Metering.GridMs.TotWOut", 30867, 2, S32, FIX0, READ_ONLY, "W", "Power grid feed-in", "Leistung Einspeisung"),
    METERING_GRIDMS_TOTWIN("Metering.GridMs.TotWIn", 30865, 2, S32, FIX0, READ_ONLY, "W", "Power drawn", "Leistung Bezug"),
    METERING_GRIDMS_TOTWHOUT("Metering.GridMs.TotWhOut", 30583, 2, U32, FIX0, READ_ONLY, "Wh", "Grid feed-in counter reading", "Zählerstand Einspeisezähler"),
    METERING_GRIDMS_TOTWHIN("Metering.GridMs.TotWhIn", 30581, 2, U32, FIX0, READ_ONLY, "Wh", "Counter reading of power drawn counter", "Zählerstand Bezugszähler"),
    NAMEPLATE_MACID("Nameplate.MacId", 40497, 16, STR32, UTF8, READ_ONLY, "-", "-", "-"),
    NAMEPLATE_PKGREV("Nameplate.PkgRev", 30059, 2, U32, FW, READ_ONLY, "-", "Software package", "Softwarepaket"),
    OPERATION_RMGTMS("Operation.RmgTms", 30199, 2, U32, DURATION, READ_ONLY, "s", "Waiting time until feed-in", "Wartezeit bis Einspeisung"),
    OPERATION_HEALTHSTT_OK("Operation.HealthStt.Ok", 30203, 2, U32, FIX0, READ_ONLY, "W", "Nominal power in Ok Mode", "Nennleistung im Zustand Ok"),
    OPERATION_HEALTHSTT_OK_2("Operation.HealthStt.Ok", 31085, 2, U32, FIX0, READ_ONLY, "W", "Nominal power in Ok Mode", "Nennleistung im Zustand Ok"),
    OPERATION_HEALTHSTT_WRN("Operation.HealthStt.Wrn", 30205, 2, U32, FIX0, READ_ONLY, "W", "Nominal power in Warning Mode", "Nennleistung im Zustand Warnung"),
    OPERATION_HEALTHSTT_ALM("Operation.HealthStt.Alm", 30207, 2, U32, FIX0, READ_ONLY, "W", "Nominal power in Fault Mode", "Nennleistung im Zustand Fehler"),
    OPERATION_HEALTH("Operation.Health", 30201, 2, U32, TAGLIST, READ_ONLY, "-", "Condition", "Zustand"),
    OPERATION_EVT_PRIO("Operation.Evt.Prio", 30211, 2, U32, TAGLIST, READ_ONLY, "-", "Recommended action", "Empfohlene Aktion"),
    OPERATION_EVT_MSG("Operation.Evt.Msg", 30213, 2, U32, TAGLIST, READ_ONLY, "-", "Message", "Meldung"),
    OPERATION_EVT_DSC("Operation.Evt.Dsc", 30215, 2, U32, TAGLIST, READ_ONLY, "-", "Fault correction measure", "Fehlerbehebungsmaßnahme"),
    OPERATION_EVTCNTUSR("Operation.EvtCntUsr", 30559, 2, U32, FIX0, READ_ONLY, "-", "Number of events for user", "Anzahl Ereignisse für Benutzer"),
    OPERATION_EVTCNTUSR_2("Operation.EvtCntUsr", 35377, 4, U64, FIX0, READ_ONLY, "-", "Number of events for user", "Anzahl Ereignisse für Benutzer"),
    OPERATION_EVTCNTISTL("Operation.EvtCntIstl", 30561, 2, U32, FIX0, READ_ONLY, "-", "Number of events for installer", "Anzahl Ereignisse für Installateur"),
    OPERATION_EVTCNTISTL_2("Operation.EvtCntIstl", 35381, 4, U64, FIX0, READ_ONLY, "-", "Number of events for installer", "Anzahl Ereignisse für Installateur"),
    OPERATION_EVTCNTSVC("Operation.EvtCntSvc", 30563, 2, U32, FIX0, READ_ONLY, "-", "Number of events for service", "Anzahl Ereignisse für Service"),
    OPERATION_EVTCNTSVC_2("Operation.EvtCntSvc", 35385, 4, U64, FIX0, READ_ONLY, "-", "Number of events for service", "Anzahl Ereignisse für Service"),
    OPERATION_EVT_EVTNO("Operation.Evt.EvtNo", 30247, 2, U32, FIX0, READ_ONLY, "-", "Current event number for manufacturer", "Aktuelle Ereignisnummer für Hersteller"),
    SPDWR_AUTOCFGISON("Spdwr.AutoCfgIsOn", 40157, 2, U32, TAGLIST, READ_WRITE, "-", "Automatic speedwire configureation switched on", "Automatische Speedwire-Konfiguration eingeschaltet"),
    SPDWR_IP("Spdwr.Ip", 40159, 8, STR32, IP4, READ_WRITE, "-", "-", "-"),
    SPDWR_SNETMSK("Spdwr.SnetMsk", 40167, 8, STR32, IP4, READ_WRITE, "-", "-", "-"),
    SPDWR_GWIP("Spdwr.GwIp", 40175, 8, STR32, IP4, READ_WRITE, "-", "-", "-"),
    SPDWR_DNSSRVIP("Spdwr.DnsSrvIp", 40513, 8, STR32, IP4, READ_WRITE, "-", "-", "-"),
    SPDWR_COMSOCA_STT("Spdwr.ComSocA.Stt", 30929, 2, U32, TAGLIST, READ_ONLY, "-", "Speedwire connection status of SMACOM A", "Speedwire-Verbindungsstatus von SMACOM A"),
    SPDWR_COMSOCA_CONNSPD("Spdwr.ComSocA.ConnSpd", 30925, 2, U32, TAGLIST, READ_ONLY, "-", "Connection speed of SMACOM A", "Verbindungsgeschwindigkeit von SMACOM A"),
    SPDWR_COMSOCA_DPXMODE("Spdwr.ComSocA.DpxMode", 30927, 2, U32, TAGLIST, READ_ONLY, "-", "Duplex mode of SMACOM A", "Duplexmodus von SMACOM A"),
    SPDWR_ACTLIP("Spdwr.ActlIp", 31017, 8, STR32, UTF8, READ_ONLY, "-", "-", "-"),
    SPDWR_ACTLSNETMSK("Spdwr.ActlSnetMsk", 31025, 8, STR32, UTF8, READ_ONLY, "-", "-", "-"),
    SPDWR_ACTLGWIP("Spdwr.ActlGwIp", 31033, 8, STR32, UTF8, READ_ONLY, "-", "-", "-"),
    SPDWR_ACTLDNSSRVIP("Spdwr.ActlDnsSrvIp", 31041, 8, STR32, UTF8, READ_ONLY, "-", "-", "-"),
    NAMEPLATE_SUSYID("Nameplate.SusyId", 30003, 2, U32, RAW, READ_ONLY, "-", "SUSyID Modul", "SUSyID Modul"),
    NAMEPLATE_SERNUM_3("Nameplate.SerNum", 30005, 2, U32, RAW, READ_ONLY, "-", "Serial number", "Seriennummer"),
    NAMEPLATE_VENDOR("Nameplate.Vendor", 30055, 2, U32, TAGLIST, READ_ONLY, "-", "Manufacturer", "Hersteller"),
    DTTM_TM("DtTm.Tm", 30193, 2, U32, DT, READ_ONLY, "-", "System time", "Systemzeit");

    private String nameId;
    private short register;
    private short count;
    private Type type;
    private Format format;
    private Access access;
    private String unit;
    private String descEn;
    private String descDe;

    SmaCode(String name, int register, int count, Type type, Format format, Access access, String unit, String descEn, String descDe) {
        this.nameId = name;
        this.register = (short)register;
        this.count = (short)count;
        this.type = type;
        this.format = format;
        this.access = access;
        this.unit = unit;
        this.descEn = descEn;
        this.descDe = descDe;
    }

    public String getName() {
        return nameId;
    }

    public short getRegister() {
        return register;
    }

    public short getCount() {
        return count;
    }

    public Type getType() {
        return type;
    }

    public Format getFormat() {
        return format;
    }

    public Access getAccess() {
        return access;
    }

    public String getUnit() {
        return unit;
    }

    public String getDescriptionEnglish() {
        return descEn;
    }

    public String getDescriptionGerman() {
        return descDe;
    }
}
