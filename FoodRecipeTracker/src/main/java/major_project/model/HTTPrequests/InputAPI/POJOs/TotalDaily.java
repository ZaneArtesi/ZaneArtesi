package major_project.model.HTTPrequests.InputAPI.POJOs;

import com.google.gson.annotations.SerializedName;

public class TotalDaily {
    @SerializedName("ENERC_KCAL")
    public GeneralNutrients eNERC_KCAL;
    @SerializedName("FAT")
    public GeneralNutrients fAT;
    @SerializedName("FASAT")
    public GeneralNutrients fASAT;
    @SerializedName("FAMS")
    public GeneralNutrients fAMS;
    @SerializedName("FAPU")
    public GeneralNutrients fAPU;
    @SerializedName("CHOCDF")
    public GeneralNutrients cHOCDF;
    @SerializedName("FIBTG")
    public GeneralNutrients fIBTG;
    @SerializedName("SUGAR")
    public GeneralNutrients sUGAR;
    @SerializedName("PROCNT")
    public GeneralNutrients pROCNT;
    @SerializedName("CHOLE")
    public GeneralNutrients cHOLE;
    @SerializedName("NA")
    public GeneralNutrients nA;
    @SerializedName("CA")
    public GeneralNutrients cA;
    @SerializedName("MG")
    public GeneralNutrients mG;
    @SerializedName("K")
    public GeneralNutrients k;
    @SerializedName("FE")
    public GeneralNutrients fE;
    @SerializedName("ZN")
    public GeneralNutrients zN;
    @SerializedName("P")
    public GeneralNutrients p;
    @SerializedName("VITA_RAE")
    public GeneralNutrients vITA_RAE;
    @SerializedName("VITC")
    public GeneralNutrients vITC;
    @SerializedName("THIA")
    public GeneralNutrients tHIA;
    @SerializedName("RIBF")
    public GeneralNutrients rIBF;
    @SerializedName("NIA")
    public GeneralNutrients nIA;
    @SerializedName("VITB6A")
    public GeneralNutrients vITB6A;
    @SerializedName("FOLDFE")
    public GeneralNutrients fOLDFE;
    @SerializedName("FOLFD")
    public GeneralNutrients fOLFD;
    @SerializedName("FOLAC")
    public GeneralNutrients fOLAC;
    @SerializedName("VITB12")
    public GeneralNutrients vITB12;
    @SerializedName("VITD")
    public GeneralNutrients vITD;
    @SerializedName("TOCPHA")
    public GeneralNutrients tOCPHA;
    @SerializedName("VITK1")
    public GeneralNutrients vITK1;
    @SerializedName("WATER")
    public GeneralNutrients wATER;

    public TotalDaily(GeneralNutrients eNERC_KCAL, GeneralNutrients fAT, GeneralNutrients fASAT,
                          GeneralNutrients fAMS, GeneralNutrients fAPU, GeneralNutrients cHOCDF,
                          GeneralNutrients fIBTG, GeneralNutrients sUGAR, GeneralNutrients pROCNT,
                          GeneralNutrients cHOLE, GeneralNutrients nA, GeneralNutrients cA, GeneralNutrients mG,
                          GeneralNutrients k, GeneralNutrients fE, GeneralNutrients zN, GeneralNutrients p,
                          GeneralNutrients vITA_RAE, GeneralNutrients vITC, GeneralNutrients tHIA,
                          GeneralNutrients rIBF, GeneralNutrients nIA, GeneralNutrients vITB6A, GeneralNutrients fOLDFE,
                          GeneralNutrients fOLFD, GeneralNutrients fOLAC, GeneralNutrients vITB12,
                          GeneralNutrients vITD, GeneralNutrients tOCPHA, GeneralNutrients vITK1, GeneralNutrients wATER) {
        this.eNERC_KCAL = eNERC_KCAL;
        this.fAT = fAT;
        this.fASAT = fASAT;
        this.fAMS = fAMS;
        this.fAPU = fAPU;
        this.cHOCDF = cHOCDF;
        this.fIBTG = fIBTG;
        this.sUGAR = sUGAR;
        this.pROCNT = pROCNT;
        this.cHOLE = cHOLE;
        this.nA = nA;
        this.cA = cA;
        this.mG = mG;
        this.k = k;
        this.fE = fE;
        this.zN = zN;
        this.p = p;
        this.vITA_RAE = vITA_RAE;
        this.vITC = vITC;
        this.tHIA = tHIA;
        this.rIBF = rIBF;
        this.nIA = nIA;
        this.vITB6A = vITB6A;
        this.fOLDFE = fOLDFE;
        this.fOLFD = fOLFD;
        this.fOLAC = fOLAC;
        this.vITB12 = vITB12;
        this.vITD = vITD;
        this.tOCPHA = tOCPHA;
        this.vITK1 = vITK1;
        this.wATER = wATER;
    }

    public GeneralNutrients geteNERC_KCAL() {
        return eNERC_KCAL;
    }

    public void seteNERC_KCAL(GeneralNutrients eNERC_KCAL) {
        this.eNERC_KCAL = eNERC_KCAL;
    }

    public GeneralNutrients getfAT() {
        return fAT;
    }

    public void setfAT(GeneralNutrients fAT) {
        this.fAT = fAT;
    }

    public GeneralNutrients getfASAT() {
        return fASAT;
    }

    public void setfASAT(GeneralNutrients fASAT) {
        this.fASAT = fASAT;
    }

    public GeneralNutrients getfAMS() {
        return fAMS;
    }

    public void setfAMS(GeneralNutrients fAMS) {
        this.fAMS = fAMS;
    }

    public GeneralNutrients getfAPU() {
        return fAPU;
    }

    public void setfAPU(GeneralNutrients fAPU) {
        this.fAPU = fAPU;
    }

    public GeneralNutrients getcHOCDF() {
        return cHOCDF;
    }

    public void setcHOCDF(GeneralNutrients cHOCDF) {
        this.cHOCDF = cHOCDF;
    }

    public GeneralNutrients getfIBTG() {
        return fIBTG;
    }

    public void setfIBTG(GeneralNutrients fIBTG) {
        this.fIBTG = fIBTG;
    }

    public GeneralNutrients getsUGAR() {
        return sUGAR;
    }

    public void setsUGAR(GeneralNutrients sUGAR) {
        this.sUGAR = sUGAR;
    }

    public GeneralNutrients getpROCNT() {
        return pROCNT;
    }

    public void setpROCNT(GeneralNutrients pROCNT) {
        this.pROCNT = pROCNT;
    }

    public GeneralNutrients getcHOLE() {
        return cHOLE;
    }

    public void setcHOLE(GeneralNutrients cHOLE) {
        this.cHOLE = cHOLE;
    }

    public GeneralNutrients getnA() {
        return nA;
    }

    public void setnA(GeneralNutrients nA) {
        this.nA = nA;
    }

    public GeneralNutrients getcA() {
        return cA;
    }

    public void setcA(GeneralNutrients cA) {
        this.cA = cA;
    }

    public GeneralNutrients getmG() {
        return mG;
    }

    public void setmG(GeneralNutrients mG) {
        this.mG = mG;
    }

    public GeneralNutrients getK() {
        return k;
    }

    public void setK(GeneralNutrients k) {
        this.k = k;
    }

    public GeneralNutrients getfE() {
        return fE;
    }

    public void setfE(GeneralNutrients fE) {
        this.fE = fE;
    }

    public GeneralNutrients getzN() {
        return zN;
    }

    public void setzN(GeneralNutrients zN) {
        this.zN = zN;
    }

    public GeneralNutrients getP() {
        return p;
    }

    public void setP(GeneralNutrients p) {
        this.p = p;
    }

    public GeneralNutrients getvITA_RAE() {
        return vITA_RAE;
    }

    public void setvITA_RAE(GeneralNutrients vITA_RAE) {
        this.vITA_RAE = vITA_RAE;
    }

    public GeneralNutrients getvITC() {
        return vITC;
    }

    public void setvITC(GeneralNutrients vITC) {
        this.vITC = vITC;
    }

    public GeneralNutrients gettHIA() {
        return tHIA;
    }

    public void settHIA(GeneralNutrients tHIA) {
        this.tHIA = tHIA;
    }

    public GeneralNutrients getrIBF() {
        return rIBF;
    }

    public void setrIBF(GeneralNutrients rIBF) {
        this.rIBF = rIBF;
    }

    public GeneralNutrients getnIA() {
        return nIA;
    }

    public void setnIA(GeneralNutrients nIA) {
        this.nIA = nIA;
    }

    public GeneralNutrients getvITB6A() {
        return vITB6A;
    }

    public void setvITB6A(GeneralNutrients vITB6A) {
        this.vITB6A = vITB6A;
    }

    public GeneralNutrients getfOLDFE() {
        return fOLDFE;
    }

    public void setfOLDFE(GeneralNutrients fOLDFE) {
        this.fOLDFE = fOLDFE;
    }

    public GeneralNutrients getfOLFD() {
        return fOLFD;
    }

    public void setfOLFD(GeneralNutrients fOLFD) {
        this.fOLFD = fOLFD;
    }

    public GeneralNutrients getfOLAC() {
        return fOLAC;
    }

    public void setfOLAC(GeneralNutrients fOLAC) {
        this.fOLAC = fOLAC;
    }

    public GeneralNutrients getvITB12() {
        return vITB12;
    }

    public void setvITB12(GeneralNutrients vITB12) {
        this.vITB12 = vITB12;
    }

    public GeneralNutrients getvITD() {
        return vITD;
    }

    public void setvITD(GeneralNutrients vITD) {
        this.vITD = vITD;
    }

    public GeneralNutrients gettOCPHA() {
        return tOCPHA;
    }

    public void settOCPHA(GeneralNutrients tOCPHA) {
        this.tOCPHA = tOCPHA;
    }

    public GeneralNutrients getvITK1() {
        return vITK1;
    }

    public void setvITK1(GeneralNutrients vITK1) {
        this.vITK1 = vITK1;
    }

    public GeneralNutrients getwATER() {
        return wATER;
    }

    public void setwATER(GeneralNutrients wATER) {
        this.wATER = wATER;
    }
}
