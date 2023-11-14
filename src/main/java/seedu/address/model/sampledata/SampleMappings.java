package seedu.address.model.sampledata;

import java.util.Arrays;

import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.mapping.Mapping;
import seedu.address.model.mapping.MappingMiscInformation;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.university.UniversityName;

/**
 * A utility class that provides sample mappings for seeding the application.
 */
public class SampleMappings {
    /**
     * The `SampleCourseCode` enum represents the sample course codes.
     */
    public enum SampleMapping {
        MAPPING_1(
            "IFS4102",
            "CSE469",
            SampleUniversities.SampleUniversityName.ARIZONA_STATE_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_2(
            "CS3244",
            "CSE494",
            SampleUniversities.SampleUniversityName.ARIZONA_STATE_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_3(
            "CS3218",
            "CS461",
            SampleUniversities.SampleUniversityName.BILKENT_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_4(
            "CS3244",
            "CS464",
            SampleUniversities.SampleUniversityName.BILKENT_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_5(
            "CS2107",
            "CTIS496",
            SampleUniversities.SampleUniversityName.BILKENT_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_6(
            "BT4240",
            "AD571",
            SampleUniversities.SampleUniversityName.BOSTON_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_7(
            "BT3103",
            "CS412",
            SampleUniversities.SampleUniversityName.BOSTON_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_8(
            "CS2106",
            "METCS575",
            SampleUniversities.SampleUniversityName.BOSTON_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_9(
            "BT4013",
            "QSTFE823",
            SampleUniversities.SampleUniversityName.BOSTON_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_10(
            "CS1010S",
            "COSI10A",
            SampleUniversities.SampleUniversityName.BRANDEIS_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_11(
            "CS3230",
            "CSDS310",
            SampleUniversities.SampleUniversityName.CASE_WESTERN_RESERVE_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_12(
            "CS3230",
            "CSDS410",
            SampleUniversities.SampleUniversityName.CASE_WESTERN_RESERVE_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_13(
            "CS3244",
            "DAT340",
            SampleUniversities.SampleUniversityName.CHALMERS_UNIVERSITY_OF_TECHNOLOGY.toValue(),
            "Semester 1"),
        MAPPING_14(
            "CS3234",
            "DAT60",
            SampleUniversities.SampleUniversityName.CHALMERS_UNIVERSITY_OF_TECHNOLOGY.toValue(),
            "Semester 2"),
        MAPPING_15(
            "CS4236",
            "TDA352",
            SampleUniversities.SampleUniversityName.CHALMERS_UNIVERSITY_OF_TECHNOLOGY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_16(
            "CS2105",
            "ICE2190472",
            SampleUniversities.SampleUniversityName.CHULALONGKORN_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_17(
            "IS3103",
            "CB3043",
            SampleUniversities.SampleUniversityName.CITY_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 2"),
        MAPPING_18(
            "CS2100",
            "CS2115",
            SampleUniversities.SampleUniversityName.CITY_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_19(
            "IS4100",
            "CS3356",
            SampleUniversities.SampleUniversityName.CITY_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 1"),
        MAPPING_20(
            "CS3240",
            "CS3483",
            SampleUniversities.SampleUniversityName.CITY_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 2"),
        MAPPING_21(
            "IS4242",
            "GE2340",
            SampleUniversities.SampleUniversityName.CITY_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_22(
            "IS4302",
            "IS3101",
            SampleUniversities.SampleUniversityName.CITY_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 1"),
        MAPPING_23(
            "IS4100",
            "IS4532",
            SampleUniversities.SampleUniversityName.CITY_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 2"),
        MAPPING_24(
            "IS4228",
            "IS4837",
            SampleUniversities.SampleUniversityName.CITY_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_25(
            "BT4211",
            "MKT4636",
            SampleUniversities.SampleUniversityName.CITY_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 1"),
        MAPPING_26(
            "BT4013",
            "MS4212",
            SampleUniversities.SampleUniversityName.CITY_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 2"),
        MAPPING_27(
            "BT4016",
            "SDSC3023",
            SampleUniversities.SampleUniversityName.CITY_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_28(
            "CS3243",
            "COMSS4701D",
            SampleUniversities.SampleUniversityName.COLUMBIA_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_29(
            "IS4100",
            "BTM480",
            SampleUniversities.SampleUniversityName.CONCORDIA_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_30(
            "CS4212",
            "COMP442",
            SampleUniversities.SampleUniversityName.CONCORDIA_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_31(
            "BT4016",
            "MACF402",
            SampleUniversities.SampleUniversityName.CONCORDIA_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_32(
            "CS4211",
            "SOEN331",
            SampleUniversities.SampleUniversityName.CONCORDIA_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_33(
            "IS3107",
            "SOEN363",
            SampleUniversities.SampleUniversityName.CONCORDIA_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_34(
            "BT4016",
            "BUSI6240",
            SampleUniversities.SampleUniversityName.DALHOUSIE_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_35(
            "CS1231",
            "CSCI1315",
            SampleUniversities.SampleUniversityName.DALHOUSIE_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_36(
            "CS2103",
            "CSCI2132",
            SampleUniversities.SampleUniversityName.DALHOUSIE_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_37(
            "CS2104",
            "CSCI3136",
            SampleUniversities.SampleUniversityName.DALHOUSIE_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_38(
            "CS4248",
            "CSCI4152",
            SampleUniversities.SampleUniversityName.DALHOUSIE_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_39(
            "BT4015",
            "INFO6681",
            SampleUniversities.SampleUniversityName.DALHOUSIE_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_40(
            "IS3103",
            "MGMT3309",
            SampleUniversities.SampleUniversityName.DALHOUSIE_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_41(
            "BT4222",
            "CS4195",
            SampleUniversities.SampleUniversityName.DELFT_UNIVERSITY_OF_TECHNOLOGY.toValue(),
            "Semester 2"),
        MAPPING_42(
            "CS3226",
            "CS4275",
            SampleUniversities.SampleUniversityName.DELFT_UNIVERSITY_OF_TECHNOLOGY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_43(
            "CS2100",
            "CSE1400",
            SampleUniversities.SampleUniversityName.DELFT_UNIVERSITY_OF_TECHNOLOGY.toValue(),
            "Semester 1"),
        MAPPING_44(
            "CS3231",
            "CSE2315",
            SampleUniversities.SampleUniversityName.DELFT_UNIVERSITY_OF_TECHNOLOGY.toValue(),
            "Semester 2"),
        MAPPING_45(
            "CS3219",
            "IN4315",
            SampleUniversities.SampleUniversityName.DELFT_UNIVERSITY_OF_TECHNOLOGY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_46(
            "IS4204",
            "SPM9640",
            SampleUniversities.SampleUniversityName.DELFT_UNIVERSITY_OF_TECHNOLOGY.toValue(),
            "Semester 1"),
        MAPPING_47(
            "IS4250",
            "TPM402A",
            SampleUniversities.SampleUniversityName.DELFT_UNIVERSITY_OF_TECHNOLOGY.toValue(),
            "Semester 2"),
        MAPPING_48(
            "CS3210",
            "TW3740TU",
            SampleUniversities.SampleUniversityName.DELFT_UNIVERSITY_OF_TECHNOLOGY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_49(
            "BT4016",
            "WI4050",
            SampleUniversities.SampleUniversityName.DELFT_UNIVERSITY_OF_TECHNOLOGY.toValue(),
            "Semester 1"),
        MAPPING_50(
            "CS3236",
            "COM404",
            SampleUniversities.SampleUniversityName.ECOLE_POLYTECHNIQUE_FEDERALE_DE_LAUSANNE.toValue(),
            "Semester 2"),
        MAPPING_51(
            "CS3244",
            "CS233",
            SampleUniversities.SampleUniversityName.ECOLE_POLYTECHNIQUE_FEDERALE_DE_LAUSANNE.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_52(
            "CS2104",
            "CS320",
            SampleUniversities.SampleUniversityName.ECOLE_POLYTECHNIQUE_FEDERALE_DE_LAUSANNE.toValue(),
            "Semester 1"),
        MAPPING_53(
            "CS3243",
            "CS410",
            SampleUniversities.SampleUniversityName.ECOLE_POLYTECHNIQUE_FEDERALE_DE_LAUSANNE.toValue(),
            "Semester 2"),
        MAPPING_54(
            "CS2107",
            "CS448",
            SampleUniversities.SampleUniversityName.ECOLE_POLYTECHNIQUE_FEDERALE_DE_LAUSANNE.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_55(
            "CS4225",
            "CS2IMD15",
            SampleUniversities.SampleUniversityName.EINDHOVEN_UNIVERSITY_OF_TECHNOLOGY.toValue(),
            "Semester 1"),
        MAPPING_56(
            "CS3219",
            "CS2IW80",
            SampleUniversities.SampleUniversityName.EINDHOVEN_UNIVERSITY_OF_TECHNOLOGY.toValue(),
            "Semester 2"),
        MAPPING_57(
            "BT1101",
            "ZXXJBM030",
            SampleUniversities.SampleUniversityName.EINDHOVEN_UNIVERSITY_OF_TECHNOLOGY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_58(
            "BT4221",
            "COMP130125",
            SampleUniversities.SampleUniversityName.FUDAN_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_59(
            "CS2030",
            "CS1331",
            SampleUniversities.SampleUniversityName.GEORGIA_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 2"),
        MAPPING_60(
            "CS3243",
            "CS3600",
            SampleUniversities.SampleUniversityName.GEORGIA_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_61(
            "CS5242",
            "CS4240",
            SampleUniversities.SampleUniversityName.GEORGIA_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 1"),
        MAPPING_62(
            "IT3010",
            "CS4400",
            SampleUniversities.SampleUniversityName.GEORGIA_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 2"),
        MAPPING_63(
            "CS4248",
            "CS4650",
            SampleUniversities.SampleUniversityName.GEORGIA_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_64(
            "IS4302",
            "CS4803",
            SampleUniversities.SampleUniversityName.GEORGIA_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 1"),
        MAPPING_65(
            "CS4212",
            "CS6241",
            SampleUniversities.SampleUniversityName.GEORGIA_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 2"),
        MAPPING_66(
            "IS4250",
            "CSE6250",
            SampleUniversities.SampleUniversityName.GEORGIA_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_67(
            "CS5224",
            "ECE4150",
            SampleUniversities.SampleUniversityName.GEORGIA_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 1"),
        MAPPING_68(
            "BT3102",
            "ISYE6416",
            SampleUniversities.SampleUniversityName.GEORGIA_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 2"),
        MAPPING_69(
            "BT3102",
            "MATH4640",
            SampleUniversities.SampleUniversityName.GEORGIA_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_70(
            "BT4013",
            "MGT3078",
            SampleUniversities.SampleUniversityName.GEORGIA_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 1"),
        MAPPING_71(
            "BT4221",
            "MGT4050",
            SampleUniversities.SampleUniversityName.GEORGIA_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 2"),
        MAPPING_72(
            "BT4211",
            "MGT4367",
            SampleUniversities.SampleUniversityName.GEORGIA_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_73(
            "CS1010",
            "ITE1010",
            SampleUniversities.SampleUniversityName.HANYANG_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_74(
            "CS3243",
            "COMP50009",
            SampleUniversities.SampleUniversityName.IMPERIAL_COLLEGE_LONDON.toValue(),
            "Semester 2"),
        MAPPING_75(
            "CS4243",
            "COMP60006",
            SampleUniversities.SampleUniversityName.IMPERIAL_COLLEGE_LONDON.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_76(
            "CS3230",
            "EE210C",
            SampleUniversities.SampleUniversityName.IMPERIAL_COLLEGE_LONDON.toValue(),
            "Semester 1"),
        MAPPING_77(
            "CS2105",
            "TC2018",
            SampleUniversities.SampleUniversityName.INST_TECH_Y_DE_ESTUDIOS_SUPERIORES_DE_MONTERREY.toValue(),
            "Semester 2"),
        MAPPING_78(
            "BT4240",
            "BUSS217",
            SampleUniversities.SampleUniversityName.KOREA_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_79(
            "CS1010E",
            "COSE101",
            SampleUniversities.SampleUniversityName.KOREA_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_80(
            "CS3241",
            "COSE331",
            SampleUniversities.SampleUniversityName.KOREA_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_81(
            "CS3240",
            "COSE432",
            SampleUniversities.SampleUniversityName.KOREA_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_82(
            "CS2030",
            "KECE443",
            SampleUniversities.SampleUniversityName.KOREA_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_83(
            "IT2002",
            "DD1334",
            SampleUniversities.SampleUniversityName.KTH_ROYAL_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 2"),
        MAPPING_84(
            "CS4268",
            "DD2367",
            SampleUniversities.SampleUniversityName.KTH_ROYAL_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_85(
            "BT4240",
            "DD2421",
            SampleUniversities.SampleUniversityName.KTH_ROYAL_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 1"),
        MAPPING_86(
            "CS5340",
            "DD2447",
            SampleUniversities.SampleUniversityName.KTH_ROYAL_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 2"),
        MAPPING_87(
            "CS3241",
            "DH2320",
            SampleUniversities.SampleUniversityName.KTH_ROYAL_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_88(
            "BT3103",
            "DH2643",
            SampleUniversities.SampleUniversityName.KTH_ROYAL_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 1"),
        MAPPING_89(
            "IS3103",
            "EH2030",
            SampleUniversities.SampleUniversityName.KTH_ROYAL_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 2"),
        MAPPING_90(
            "CS3244",
            "EL2810",
            SampleUniversities.SampleUniversityName.KTH_ROYAL_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_91(
            "CS3236",
            "EQ2845",
            SampleUniversities.SampleUniversityName.KTH_ROYAL_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 1"),
        MAPPING_92(
            "CS3211",
            "ID1217",
            SampleUniversities.SampleUniversityName.KTH_ROYAL_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 2"),
        MAPPING_93(
            "IS3251",
            "ME2062",
            SampleUniversities.SampleUniversityName.KTH_ROYAL_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_94(
            "CS3244",
            "SF2957",
            SampleUniversities.SampleUniversityName.KTH_ROYAL_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 1"),
        MAPPING_95(
            "CS3244",
            "T057001",
            SampleUniversities.SampleUniversityName.KYOTO_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_96(
            "CS1010",
            "CSE1OOF",
            SampleUniversities.SampleUniversityName.LATROBE_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_97(
            "BT4221",
            "TDDE31",
            SampleUniversities.SampleUniversityName.LINKOPING_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_98(
            "CS3230",
            "EDAF05",
            SampleUniversities.SampleUniversityName.LUND_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_99(
            "CS4247",
            "EDAN35",
            SampleUniversities.SampleUniversityName.LUND_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_100(
            "CS1231",
            "FMAA25",
            SampleUniversities.SampleUniversityName.LUND_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_101(
            "CS2107",
            "INFC40",
            SampleUniversities.SampleUniversityName.LUND_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_102(
            "BT4012",
            "ACCT475",
            SampleUniversities.SampleUniversityName.MCGILL_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_103(
            "CS3219",
            "COMP303",
            SampleUniversities.SampleUniversityName.MCGILL_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_104(
            "CS2106",
            "COMP310",
            SampleUniversities.SampleUniversityName.MCGILL_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_105(
            "CS3230",
            "COMP360",
            SampleUniversities.SampleUniversityName.MCGILL_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_106(
            "CS4248",
            "COMP550",
            SampleUniversities.SampleUniversityName.MCGILL_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_107(
            "CS3244",
            "ECSE551",
            SampleUniversities.SampleUniversityName.MCGILL_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_108(
            "BT4013",
            "FINE442",
            SampleUniversities.SampleUniversityName.MCGILL_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_109(
            "BT3103",
            "INSY341",
            SampleUniversities.SampleUniversityName.MCGILL_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_110(
            "IS1108",
            "FIT1055",
            SampleUniversities.SampleUniversityName.MONASH_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_111(
            "CS2106",
            "FIT2100",
            SampleUniversities.SampleUniversityName.MONASH_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_112(
            "CS3230",
            "FIT3155",
            SampleUniversities.SampleUniversityName.MONASH_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_113(
            "IS3103",
            "AB0602",
            SampleUniversities.SampleUniversityName.NANYANG_TECHNOLOGICAL_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_114(
            "CS4242",
            "CS4022",
            SampleUniversities.SampleUniversityName.NANYANG_TECHNOLOGICAL_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_115(
            "CS2106",
            "CZ2005",
            SampleUniversities.SampleUniversityName.NANYANG_TECHNOLOGICAL_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_116(
            "CS4243",
            "CZ4003",
            SampleUniversities.SampleUniversityName.NANYANG_TECHNOLOGICAL_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_117(
            "CS3223",
            "CZ4031",
            SampleUniversities.SampleUniversityName.NANYANG_TECHNOLOGICAL_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_118(
            "CS3235",
            "CZ4062",
            SampleUniversities.SampleUniversityName.NANYANG_TECHNOLOGICAL_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_119(
            "IS4242",
            "EE4208",
            SampleUniversities.SampleUniversityName.NANYANG_TECHNOLOGICAL_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_120(
            "IT3011",
            "EE4414",
            SampleUniversities.SampleUniversityName.NANYANG_TECHNOLOGICAL_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_121(
            "BT3103",
            "EE4717",
            SampleUniversities.SampleUniversityName.NANYANG_TECHNOLOGICAL_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_122(
            "CS2102",
            "IE4791",
            SampleUniversities.SampleUniversityName.NANYANG_TECHNOLOGICAL_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_123(
            "CS1231",
            "MH1301",
            SampleUniversities.SampleUniversityName.NANYANG_TECHNOLOGICAL_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_124(
            "BT4013",
            "MH4500",
            SampleUniversities.SampleUniversityName.NANYANG_TECHNOLOGICAL_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_125(
            "CS4249",
            "SC3061",
            SampleUniversities.SampleUniversityName.NANYANG_TECHNOLOGICAL_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_126(
            "CS2107",
            "DCP3109",
            SampleUniversities.SampleUniversityName.NATIONAL_CHENG_KUNG_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_127(
            "IS3103",
            "DOM1010",
            SampleUniversities.SampleUniversityName.NATIONAL_CHIAO_TUNG_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_128(
            "CS4243",
            "ILE5062",
            SampleUniversities.SampleUniversityName.NATIONAL_CHIAO_TUNG_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_129(
            "BT4016",
            "IMS2335",
            SampleUniversities.SampleUniversityName.NATIONAL_CHIAO_TUNG_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_130(
            "CS3230",
            "IOC5194",
            SampleUniversities.SampleUniversityName.NATIONAL_CHIAO_TUNG_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_131(
            "CS4236",
            "UEE4611",
            SampleUniversities.SampleUniversityName.NATIONAL_CHIAO_TUNG_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_132(
            "CS2105",
            "CSIE3510",
            SampleUniversities.SampleUniversityName.NATIONAL_TAIWAN_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_133(
            "CS3210",
            "CSIE5213",
            SampleUniversities.SampleUniversityName.NATIONAL_TAIWAN_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_134(
            "CS2107",
            "CSIE7016",
            SampleUniversities.SampleUniversityName.NATIONAL_TAIWAN_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_135(
            "IS4241",
            "IBM6176",
            SampleUniversities.SampleUniversityName.NATIONAL_TSING_HUA_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_136(
            "CS3230",
            "IEE510900",
            SampleUniversities.SampleUniversityName.NATIONAL_TSING_HUA_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_137(
            "IS4242",
            "ISA5810",
            SampleUniversities.SampleUniversityName.NATIONAL_TSING_HUA_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_138(
            "BT4301",
            "ISS509000",
            SampleUniversities.SampleUniversityName.NATIONAL_TSING_HUA_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_139(
            "IS4302",
            "AICA30005",
            SampleUniversities.SampleUniversityName.NATIONAL_YANG_MING_CHIAO_TUNG_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_140(
            "CS3241",
            "CS550000",
            SampleUniversities.SampleUniversityName.NATIONAL_YANG_MING_CHIAO_TUNG_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_141(
            "CS2107",
            "CSC0072",
            SampleUniversities.SampleUniversityName.NATIONAL_YANG_MING_CHIAO_TUNG_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_142(
            "CS3244",
            "CSCS20024",
            SampleUniversities.SampleUniversityName.NATIONAL_YANG_MING_CHIAO_TUNG_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_143(
            "IS4302",
            "CSIC30161",
            SampleUniversities.SampleUniversityName.NATIONAL_YANG_MING_CHIAO_TUNG_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_144(
            "CS3230",
            "MGIM30048",
            SampleUniversities.SampleUniversityName.NATIONAL_YANG_MING_CHIAO_TUNG_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_145(
            "CS2040",
            "EECS214",
            SampleUniversities.SampleUniversityName.NORTHWESTERN_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_146(
            "IS4250",
            "IEMS385",
            SampleUniversities.SampleUniversityName.NORTHWESTERN_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_147(
            "CS2104",
            "CS04830270",
            SampleUniversities.SampleUniversityName.PEKING_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_148(
            "CS4268",
            "CS04834980",
            SampleUniversities.SampleUniversityName.PEKING_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_149(
            "CS4243",
            "CS4835030",
            SampleUniversities.SampleUniversityName.PEKING_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_150(
            "CS2109S",
            "CMPSC448",
            SampleUniversities.SampleUniversityName.PENNSYLVANIA_STATE_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_151(
            "BT4240",
            "DS310",
            SampleUniversities.SampleUniversityName.PENNSYLVANIA_STATE_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_152(
            "BT4013",
            "FIN414",
            SampleUniversities.SampleUniversityName.PENNSYLVANIA_STATE_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_153(
            "IS4100",
            "IST302",
            SampleUniversities.SampleUniversityName.PENNSYLVANIA_STATE_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_154(
            "IS4233",
            "IST432",
            SampleUniversities.SampleUniversityName.PENNSYLVANIA_STATE_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_155(
            "CS3211",
            "CS35300",
            SampleUniversities.SampleUniversityName.PURDUE_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_156(
            "CS3264",
            "CS37300",
            SampleUniversities.SampleUniversityName.PURDUE_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_157(
            "CS3230",
            "CS38100",
            SampleUniversities.SampleUniversityName.PURDUE_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_158(
            "CS2105",
            "CS42200",
            SampleUniversities.SampleUniversityName.PURDUE_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_159(
            "CS3211",
            "CS52500",
            SampleUniversities.SampleUniversityName.PURDUE_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_160(
            "CS1231",
            "MA37500",
            SampleUniversities.SampleUniversityName.PURDUE_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_161(
            "CS3231",
            "CS644",
            SampleUniversities.SampleUniversityName.REICHMAN_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_162(
            "CS4218",
            "CS1212356",
            SampleUniversities.SampleUniversityName.RWTH_AACHEN_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_163(
            "CS3241",
            "CMPT361",
            SampleUniversities.SampleUniversityName.SIMON_FRASER_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_164(
            "CS4244",
            "CMPT411",
            SampleUniversities.SampleUniversityName.SIMON_FRASER_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_165(
            "CS4211",
            "CMPT477",
            SampleUniversities.SampleUniversityName.SIMON_FRASER_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_166(
            "CS2102",
            "CPMT354",
            SampleUniversities.SampleUniversityName.SIMON_FRASER_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_167(
            "CS2105",
            "CS204",
            SampleUniversities.SampleUniversityName.SINGAPORE_MANAGEMENT_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_168(
            "CS4239",
            "CS443",
            SampleUniversities.SampleUniversityName.SINGAPORE_MANAGEMENT_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_169(
            "CS1010",
            "IS111",
            SampleUniversities.SampleUniversityName.SINGAPORE_MANAGEMENT_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_170(
            "CS2105",
            "IS204",
            SampleUniversities.SampleUniversityName.SINGAPORE_MANAGEMENT_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_171(
            "BT3103",
            "IS216",
            SampleUniversities.SampleUniversityName.SINGAPORE_MANAGEMENT_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_172(
            "BT4212",
            "IS414",
            SampleUniversities.SampleUniversityName.SINGAPORE_MANAGEMENT_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_173(
            "BT2102",
            "IS428",
            SampleUniversities.SampleUniversityName.SINGAPORE_MANAGEMENT_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_174(
            "IS4228",
            "IS445",
            SampleUniversities.SampleUniversityName.SINGAPORE_MANAGEMENT_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_175(
            "IS4242",
            "IS460",
            SampleUniversities.SampleUniversityName.SINGAPORE_MANAGEMENT_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_176(
            "IS4233",
            "LAW486",
            SampleUniversities.SampleUniversityName.SINGAPORE_MANAGEMENT_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_177(
            "BT4016",
            "QF307",
            SampleUniversities.SampleUniversityName.SINGAPORE_MANAGEMENT_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_178(
            "CS3241",
            "CS148",
            SampleUniversities.SampleUniversityName.STANFORD_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_179(
            "CS2106",
            "CS323",
            SampleUniversities.SampleUniversityName.SWISS_FEDERAL_INSTITUTE_OF_TECHNOLOGY_LAUSANNE.toValue(),
            "Semester 2"),
        MAPPING_180(
            "CS2106",
            "CS330",
            SampleUniversities.SampleUniversityName.SWISS_FEDERAL_INSTITUTE_OF_TECHNOLOGY_LAUSANNE.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_181(
            "CS2105",
            "CS341",
            SampleUniversities.SampleUniversityName.SWISS_FEDERAL_INSTITUTE_OF_TECHNOLOGY_LAUSANNE.toValue(),
            "Semester 1"),
        MAPPING_182(
            "BT3102",
            "CS422",
            SampleUniversities.SampleUniversityName.SWISS_FEDERAL_INSTITUTE_OF_TECHNOLOGY_LAUSANNE.toValue(),
            "Semester 2"),
        MAPPING_183(
            "CS3210",
            "CS431",
            SampleUniversities.SampleUniversityName.SWISS_FEDERAL_INSTITUTE_OF_TECHNOLOGY_LAUSANNE.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_184(
            "CS2104",
            "CS452",
            SampleUniversities.SampleUniversityName.SWISS_FEDERAL_INSTITUTE_OF_TECHNOLOGY_LAUSANNE.toValue(),
            "Semester 1"),
        MAPPING_185(
            "CS3240",
            "CS486",
            SampleUniversities.SampleUniversityName.SWISS_FEDERAL_INSTITUTE_OF_TECHNOLOGY_LAUSANNE.toValue(),
            "Semester 2"),
        MAPPING_186(
            "CS5272",
            "ITS8050",
            SampleUniversities.SampleUniversityName.TALLINN_UNIVERSITY_OF_TECHNOLOGY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_187(
            "CS4230",
            "CS01410",
            SampleUniversities.SampleUniversityName.TECHNICAL_UNIVERSITY_OF_DENMARK.toValue(),
            "Semester 1"),
        MAPPING_188(
            "CS4243",
            "CS02502",
            SampleUniversities.SampleUniversityName.TECHNICAL_UNIVERSITY_OF_DENMARK.toValue(),
            "Semester 2"),
        MAPPING_189(
            "CS4277",
            "IN0014",
            SampleUniversities.SampleUniversityName.TECHNICAL_UNIVERSITY_OF_MUNICH.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_190(
            "CS3241",
            "IN2017",
            SampleUniversities.SampleUniversityName.TECHNICAL_UNIVERSITY_OF_MUNICH.toValue(),
            "Semester 1"),
        MAPPING_191(
            "CS3243",
            "IN2062",
            SampleUniversities.SampleUniversityName.TECHNICAL_UNIVERSITY_OF_MUNICH.toValue(),
            "Semester 2"),
        MAPPING_192(
            "CS3226",
            "IN2087",
            SampleUniversities.SampleUniversityName.TECHNICAL_UNIVERSITY_OF_MUNICH.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_193(
            "CS3223",
            "IN2117",
            SampleUniversities.SampleUniversityName.TECHNICAL_UNIVERSITY_OF_MUNICH.toValue(),
            "Semester 1"),
        MAPPING_194(
            "CS4243",
            "IN2228",
            SampleUniversities.SampleUniversityName.TECHNICAL_UNIVERSITY_OF_MUNICH.toValue(),
            "Semester 2"),
        MAPPING_195(
            "CS3244",
            "IN2332",
            SampleUniversities.SampleUniversityName.TECHNICAL_UNIVERSITY_OF_MUNICH.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_196(
            "CS2105",
            "IN2380",
            SampleUniversities.SampleUniversityName.TECHNICAL_UNIVERSITY_OF_MUNICH.toValue(),
            "Semester 1"),
        MAPPING_197(
            "CS4345",
            "IN4102",
            SampleUniversities.SampleUniversityName.TECHNICAL_UNIVERSITY_OF_MUNICH.toValue(),
            "Semester 2"),
        MAPPING_198(
            "CS2030",
            "COMP1140",
            SampleUniversities.SampleUniversityName.THE_AUSTRALIAN_NATIONAL_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_199(
            "BT3103",
            "COMP1710",
            SampleUniversities.SampleUniversityName.THE_AUSTRALIAN_NATIONAL_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_200(
            "CS3243",
            "COMP3620",
            SampleUniversities.SampleUniversityName.THE_AUSTRALIAN_NATIONAL_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_201(
            "CS3245",
            "COMP4650",
            SampleUniversities.SampleUniversityName.THE_AUSTRALIAN_NATIONAL_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_202(
            "BT4013",
            "FINM1001",
            SampleUniversities.SampleUniversityName.THE_AUSTRALIAN_NATIONAL_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_203(
            "IS4152",
            "AIST3510",
            SampleUniversities.SampleUniversityName.THE_CHINESE_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 2"),
        MAPPING_204(
            "CS2100",
            "CSCI2510",
            SampleUniversities.SampleUniversityName.THE_CHINESE_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_205(
            "CS3223",
            "CSCI3170",
            SampleUniversities.SampleUniversityName.THE_CHINESE_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 1"),
        MAPPING_206(
            "CS2109S",
            "CSCI3230",
            SampleUniversities.SampleUniversityName.THE_CHINESE_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 2"),
        MAPPING_207(
            "CS5224",
            "CSCI4180",
            SampleUniversities.SampleUniversityName.THE_CHINESE_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_208(
            "IS4231",
            "DSME4280",
            SampleUniversities.SampleUniversityName.THE_CHINESE_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 1"),
        MAPPING_209(
            "IS4242",
            "FETC5580",
            SampleUniversities.SampleUniversityName.THE_CHINESE_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 2"),
        MAPPING_210(
            "IS4151",
            "IERG4230",
            SampleUniversities.SampleUniversityName.THE_CHINESE_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_211(
            "IS3150",
            "MKTG4080",
            SampleUniversities.SampleUniversityName.THE_CHINESE_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 1"),
        MAPPING_212(
            "IS4243",
            "SEEM3680",
            SampleUniversities.SampleUniversityName.THE_CHINESE_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 2"),
        MAPPING_213(
            "CS3231",
            "CSCI3313",
            SampleUniversities.SampleUniversityName.THE_GEORGE_WASHINGTON_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_214(
            "IS4302",
            "CSCI3907",
            SampleUniversities.SampleUniversityName.THE_GEORGE_WASHINGTON_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_215(
            "CS3244",
            "CSCI4364",
            SampleUniversities.SampleUniversityName.THE_GEORGE_WASHINGTON_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_216(
            "CS3241",
            "CSCI4554",
            SampleUniversities.SampleUniversityName.THE_GEORGE_WASHINGTON_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_217(
            "IS4261",
            "COMP2121",
            SampleUniversities.SampleUniversityName.THE_HONG_KONG_POLYTECHNIC_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_218(
            "CS2102",
            "COMP2411",
            SampleUniversities.SampleUniversityName.THE_HONG_KONG_POLYTECHNIC_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_219(
            "CS3230",
            "COMP3011",
            SampleUniversities.SampleUniversityName.THE_HONG_KONG_POLYTECHNIC_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_220(
            "CS2103",
            "COMP3211",
            SampleUniversities.SampleUniversityName.THE_HONG_KONG_POLYTECHNIC_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_221(
            "CS3235",
            "COMP3334",
            SampleUniversities.SampleUniversityName.THE_HONG_KONG_POLYTECHNIC_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_222(
            "CS2108",
            "COMP3422",
            SampleUniversities.SampleUniversityName.THE_HONG_KONG_POLYTECHNIC_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_223(
            "IS4228",
            "COMP3921",
            SampleUniversities.SampleUniversityName.THE_HONG_KONG_POLYTECHNIC_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_224(
            "IS4302",
            "COMP4142",
            SampleUniversities.SampleUniversityName.THE_HONG_KONG_POLYTECHNIC_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_225(
            "CS5224",
            "COMP4442",
            SampleUniversities.SampleUniversityName.THE_HONG_KONG_POLYTECHNIC_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_226(
            "BT4221",
            "HTM4350",
            SampleUniversities.SampleUniversityName.THE_HONG_KONG_POLYTECHNIC_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_227(
            "CS3226",
            "CSC337",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_ARIZONA.toValue(),
            "Semester 2"),
        MAPPING_228(
            "CS3241",
            "CSC433",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_ARIZONA.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_229(
            "CS4248",
            "ISTA455",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_ARIZONA.toValue(),
            "Semester 1"),
        MAPPING_230(
            "CS3243",
            "COMPSCI367",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_AUCKLAND.toValue(),
            "Semester 2"),
        MAPPING_231(
            "BT2101",
            "ECON321",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_AUCKLAND.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_232(
            "IS3103",
            "COMM205",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_BRITISH_COLUMBIA.toValue(),
            "Semester 1"),
        MAPPING_233(
            "BT3103",
            "COMM436",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_BRITISH_COLUMBIA.toValue(),
            "Semester 2"),
        MAPPING_234(
            "CS3230",
            "COSC320",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_BRITISH_COLUMBIA.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_235(
            "CS3231",
            "CPSC313",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_BRITISH_COLUMBIA.toValue(),
            "Semester 1"),
        MAPPING_236(
            "BT4240",
            "CPSC340",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_BRITISH_COLUMBIA.toValue(),
            "Semester 2"),
        MAPPING_237(
            "CS4249",
            "CPSC444",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_BRITISH_COLUMBIA.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_238(
            "IS3103",
            "BUST08028",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_EDINBURGH.toValue(),
            "Semester 1"),
        MAPPING_239(
            "IS3103",
            "CMSE10002",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_EDINBURGH.toValue(),
            "Semester 2"),
        MAPPING_240(
            "CS1231",
            "INFR08031",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_EDINBURGH.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_241(
            "IS3240",
            "INFR10064",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_EDINBURGH.toValue(),
            "Semester 1"),
        MAPPING_242(
            "CS3244",
            "INFR10086",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_EDINBURGH.toValue(),
            "Semester 2"),
        MAPPING_243(
            "CS3219",
            "INFR11038",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_EDINBURGH.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_244(
            "IS4241",
            "INFR11124",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_EDINBURGH.toValue(),
            "Semester 1"),
        MAPPING_245(
            "CS4243",
            "INFR11140",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_EDINBURGH.toValue(),
            "Semester 2"),
        MAPPING_246(
            "CS3264",
            "INFR11211",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_EDINBURGH.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_247(
            "CS3211",
            "INFR11226",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_EDINBURGH.toValue(),
            "Semester 1"),
        MAPPING_248(
            "CS3211",
            "INFR11248",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_EDINBURGH.toValue(),
            "Semester 2"),
        MAPPING_249(
            "CS3230",
            "COMP3251",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_250(
            "CS3244",
            "COMP3314",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 1"),
        MAPPING_251(
            "CS4236",
            "COMP3357",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 2"),
        MAPPING_252(
            "IT3010",
            "IIMT3601",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_253(
            "CS3244",
            "STAT3612",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_HONG_KONG.toValue(),
            "Semester 1"),
        MAPPING_254(
            "IS4228",
            "BMAN20792",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_MANCHESTER.toValue(),
            "Semester 2"),
        MAPPING_255(
            "IS4226",
            "MATH39032",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_MANCHESTER.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_256(
            "IS3251",
            "MCEL30011",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_MANCHESTER.toValue(),
            "Semester 1"),
        MAPPING_257(
            "CS2107",
            "COMP30006",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_MELBOURNE.toValue(),
            "Semester 2"),
        MAPPING_258(
            "CS4269",
            "COMP30026",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_MELBOURNE.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_259(
            "CS2105",
            "COMP90007",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_MELBOURNE.toValue(),
            "Semester 1"),
        MAPPING_260(
            "CS3223",
            "COMP90050",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_MELBOURNE.toValue(),
            "Semester 2"),
        MAPPING_261(
            "CS3240",
            "INFO10003",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_MELBOURNE.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_262(
            "CS3213",
            "SWEN90007",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_MELBOURNE.toValue(),
            "Semester 1"),
        MAPPING_263(
            "CS2106",
            "COMP3231",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_NEW_SOUTH_WALES.toValue(),
            "Semester 2"),
        MAPPING_264(
            "CS5242",
            "COMP9444",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_NEW_SOUTH_WALES.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_265(
            "IS4100",
            "INFS3830",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_NEW_SOUTH_WALES.toValue(),
            "Semester 1"),
        MAPPING_266(
            "CS2030",
            "COMP1009",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_NOTTINGHAM.toValue(),
            "Semester 2"),
        MAPPING_267(
            "CS2109S",
            "COMP2011",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_NOTTINGHAM.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_268(
            "CS3219",
            "COMP2103",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_NOTTINGHAM.toValue(),
            "Semester 1"),
        MAPPING_269(
            "CS4244",
            "COMP3008",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_NOTTINGHAM.toValue(),
            "Semester 2"),
        MAPPING_270(
            "CS4225",
            "COMP4124",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_NOTTINGHAM.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_271(
            "BT4240",
            "BSAN2205",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_QUEENSLAND.toValue(),
            "Semester 1"),
        MAPPING_272(
            "IS4241",
            "MKTG3506",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_QUEENSLAND.toValue(),
            "Semester 2"),
        MAPPING_273(
            "IS4262",
            "TIMS2302",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_QUEENSLAND.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_274(
            "CS4278",
            "ACS323",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_SHEFFIELD.toValue(),
            "Semester 1"),
        MAPPING_275(
            "CS2113",
            "COM162",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_SHEFFIELD.toValue(),
            "Semester 2"),
        MAPPING_276(
            "CS3226",
            "COM3501",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_SHEFFIELD.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_277(
            "CS2106",
            "EEE6207",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_SHEFFIELD.toValue(),
            "Semester 1"),
        MAPPING_278(
            "BT4016",
            "BANK3011",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_SYDNEY.toValue(),
            "Semester 2"),
        MAPPING_279(
            "BT4221",
            "DATA2901",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_SYDNEY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_280(
            "CS4249",
            "INFO3315",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_SYDNEY.toValue(),
            "Semester 1"),
        MAPPING_281(
            "IS1103",
            "ISYS2160",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_SYDNEY.toValue(),
            "Semester 2"),
        MAPPING_282(
            "BT3102",
            "QBUS2830",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_SYDNEY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_283(
            "BT4016",
            "QBUS6820",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_SYDNEY.toValue(),
            "Semester 1"),
        MAPPING_284(
            "CS3244",
            "STAT3888",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_SYDNEY.toValue(),
            "Semester 2"),
        MAPPING_285(
            "CS3230",
            "ECE360C",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_TEXAS_AT_AUSTIN.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_286(
            "CS3211",
            "EE361C",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_TEXAS_AT_AUSTIN.toValue(),
            "Semester 1"),
        MAPPING_287(
            "IS4261",
            "MIS374",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_TEXAS_AT_AUSTIN.toValue(),
            "Semester 2"),
        MAPPING_288(
            "CS3243",
            "CITS3001",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_WESTERN_AUSTRALIA.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_289(
            "BT4014",
            "CITS4009",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_WESTERN_AUSTRALIA.toValue(),
            "Semester 1"),
        MAPPING_290(
            "CS3210",
            "CITS5507",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_WESTERN_AUSTRALIA.toValue(),
            "Semester 2"),
        MAPPING_291(
            "BT4015",
            "STAT3063",
            SampleUniversities.SampleUniversityName.THE_UNIVERSITY_OF_WESTERN_AUSTRALIA.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_292(
            "CS3244",
            "TEIPRI310J",
            SampleUniversities.SampleUniversityName.TOHOKU_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_293(
            "CS4243",
            "ARTT467",
            SampleUniversities.SampleUniversityName.TOKYO_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 2"),
        MAPPING_294(
            "CS2102",
            "CSCT343",
            SampleUniversities.SampleUniversityName.TOKYO_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_295(
            "CS4231",
            "CSCT438",
            SampleUniversities.SampleUniversityName.TOKYO_INSTITUTE_OF_TECHNOLOGY.toValue(),
            "Semester 1"),
        MAPPING_296(
            "CS4248",
            "CSU22062",
            SampleUniversities.SampleUniversityName.TRINITY_COLLEGE_DUBLIN.toValue(),
            "Semester 2"),
        MAPPING_297(
            "CS3226",
            "CSU44000",
            SampleUniversities.SampleUniversityName.TRINITY_COLLEGE_DUBLIN.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_298(
            "CS4243",
            "CSU44053",
            SampleUniversities.SampleUniversityName.TRINITY_COLLEGE_DUBLIN.toValue(),
            "Semester 1"),
        MAPPING_299(
            "CS3230",
            "CS30470124",
            SampleUniversities.SampleUniversityName.TSINGHUA_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_300(
            "CS4243",
            "CMPS3660",
            SampleUniversities.SampleUniversityName.TULANE_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_301(
            "CS3230",
            "COMP20290",
            SampleUniversities.SampleUniversityName.UNIVERSITY_COLLEGE_DUBLIN.toValue(),
            "Semester 1"),
        MAPPING_302(
            "CS4226",
            "COMP30650",
            SampleUniversities.SampleUniversityName.UNIVERSITY_COLLEGE_DUBLIN.toValue(),
            "Semester 2"),
        MAPPING_303(
            "CS2220",
            "COMP40400",
            SampleUniversities.SampleUniversityName.UNIVERSITY_COLLEGE_DUBLIN.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_304(
            "CS3219",
            "COMP4748",
            SampleUniversities.SampleUniversityName.UNIVERSITY_COLLEGE_DUBLIN.toValue(),
            "Semester 1"),
        MAPPING_305(
            "CS1010S",
            "COMP0015",
            SampleUniversities.SampleUniversityName.UNIVERSITY_COLLEGE_LONDON.toValue(),
            "Semester 2"),
        MAPPING_306(
            "IS3221",
            "MN20014",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_BATH.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_307(
            "CS4243",
            "CS19339",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_BIRMINGHAM.toValue(),
            "Semester 1"),
        MAPPING_308(
            "CS3241",
            "COMS30018",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_BRISTOL.toValue(),
            "Semester 2"),
        MAPPING_309(
            "CS3230",
            "COMS30042",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_BRISTOL.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_310(
            "BT3104",
            "EMAT30670",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_BRISTOL.toValue(),
            "Semester 1"),
        MAPPING_311(
            "CS3230",
            "CPSC413",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_CALGARY.toValue(),
            "Semester 2"),
        MAPPING_312(
            "CS2109S",
            "CPSC433",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_CALGARY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_313(
            "CS4268",
            "CPSC519",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_CALGARY.toValue(),
            "Semester 1"),
        MAPPING_314(
            "CS3244",
            "CPSC544",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_CALGARY.toValue(),
            "Semester 2"),
        MAPPING_315(
            "CS1231",
            "MATH271",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_CALGARY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_316(
            "CS3219",
            "SENG471",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_CALGARY.toValue(),
            "Semester 1"),
        MAPPING_317(
            "CS3241",
            "CSCI4229",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_COLORADO_BOULDER.toValue(),
            "Semester 2"),
        MAPPING_318(
            "CS2107",
            "ECEN4133",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_COLORADO_BOULDER.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_319(
            "BT4013",
            "FNCE3030",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_COLORADO_BOULDER.toValue(),
            "Semester 1"),
        MAPPING_320(
            "CS2103",
            "CES2102",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_CONNECTICUT.toValue(),
            "Semester 2"),
        MAPPING_321(
            "IS4302",
            "CSE2550",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_CONNECTICUT.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_322(
            "CS2107",
            "CSE3400",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_CONNECTICUT.toValue(),
            "Semester 1"),
        MAPPING_323(
            "CS2106",
            "CSE4300",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_CONNECTICUT.toValue(),
            "Semester 2"),
        MAPPING_324(
            "BT4015",
            "GEOG2500",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_CONNECTICUT.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_325(
            "CS3244",
            "NDAB1800",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_COPENHAGEN.toValue(),
            "Semester 1"),
        MAPPING_326(
            "CS2106",
            "CSCI4730",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_GEORGIA.toValue(),
            "Semester 2"),
        MAPPING_327(
            "CS4244",
            "CSCI8050",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_GEORGIA.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_328(
            "IS3251",
            "ENTR5500",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_GEORGIA.toValue(),
            "Semester 1"),
        MAPPING_329(
            "IS4228",
            "MIST4700",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_GEORGIA.toValue(),
            "Semester 2"),
        MAPPING_330(
            "IS3221",
            "MIST5750",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_GEORGIA.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_331(
            "IS4100",
            "DIT885",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_GOTEBORG.toValue(),
            "Semester 1"),
        MAPPING_332(
            "CS5321",
            "DIT071",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_GOTHENBURG.toValue(),
            "Semester 2"),
        MAPPING_333(
            "CS4249",
            "DIT157",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_GOTHENBURG.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_334(
            "CS4247",
            "DIT227",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_GOTHENBURG.toValue(),
            "Semester 1"),
        MAPPING_335(
            "CS3219",
            "DIT345",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_GOTHENBURG.toValue(),
            "Semester 2"),
        MAPPING_336(
            "CS2105",
            "DIT423",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_GOTHENBURG.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_337(
            "CS3103",
            "DIT671",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_GOTHENBURG.toValue(),
            "Semester 1"),
        MAPPING_338(
            "CS1231",
            "CIS1910",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_GUELPH.toValue(),
            "Semester 2"),
        MAPPING_339(
            "CS2106",
            "CIS3110",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_GUELPH.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_340(
            "CS3213",
            "CIS3750",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_GUELPH.toValue(),
            "Semester 1"),
        MAPPING_341(
            "CS3241",
            "CIS4800",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_GUELPH.toValue(),
            "Semester 2"),
        MAPPING_342(
            "CS3219",
            "HBV204M",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_ICELAND.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_343(
            "CS3231",
            "COMP2321",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_LEEDS.toValue(),
            "Semester 1"),
        MAPPING_344(
            "CS2100",
            "COMP2611",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_LEEDS.toValue(),
            "Semester 2"),
        MAPPING_345(
            "CS4278",
            "COMP329",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_LIVERPOOL.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_346(
            "IS4100",
            "EBUS308",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_LIVERPOOL.toValue(),
            "Semester 1"),
        MAPPING_347(
            "CS3230",
            "CS500",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_MANNHEIM.toValue(),
            "Semester 2"),
        MAPPING_348(
            "IS3103",
            "MAN645",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_MANNHEIM.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_349(
            "BT4240",
            "OPM562",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_MANNHEIM.toValue(),
            "Semester 1"),
        MAPPING_350(
            "CS4218",
            "CSC410",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_MIAMI.toValue(),
            "Semester 2"),
        MAPPING_351(
            "CS3244",
            "ECE548",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_MIAMI.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_352(
            "CS3210",
            "CSCI5451",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_MINNESOTA.toValue(),
            "Semester 1"),
        MAPPING_353(
            "CS2107",
            "INET4007",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_MINNESOTA.toValue(),
            "Semester 2"),
        MAPPING_354(
            "CS3245",
            "IN3120",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_OSLO.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_355(
            "IS4242",
            "IN5490",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_OSLO.toValue(),
            "Semester 1"),
        MAPPING_356(
            "CS3245",
            "COCS431",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_OTAGO.toValue(),
            "Semester 2"),
        MAPPING_357(
            "CS3240",
            "INFO203",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_OTAGO.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_358(
            "IS4242",
            "MART448",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_OTAGO.toValue(),
            "Semester 1"),
        MAPPING_359(
            "CS2106",
            "CSI3131",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_OTTAWA.toValue(),
            "Semester 2"),
        MAPPING_360(
            "CS3243",
            "CSI4108",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_OTTAWA.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_361(
            "CS2107",
            "CSI4139",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_OTTAWA.toValue(),
            "Semester 1"),
        MAPPING_362(
            "BT4240",
            "MAT4373",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_OTTAWA.toValue(),
            "Semester 2"),
        MAPPING_363(
            "BT3102",
            "MATH320",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_PENNSYLVANIA.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_364(
            "BT4240",
            "PHYS360",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_PENNSYLVANIA.toValue(),
            "Semester 1"),
        MAPPING_365(
            "CS2102",
            "CS1555",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_PITTSBURGH.toValue(),
            "Semester 2"),
        MAPPING_366(
            "CS2105",
            "CS1652",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_PITTSBURGH.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_367(
            "CS4243",
            "CS1674",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_PITTSBURGH.toValue(),
            "Semester 1"),
        MAPPING_368(
            "CS4248",
            "CS2731",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_PITTSBURGH.toValue(),
            "Semester 2"),
        MAPPING_369(
            "CS2102",
            "INFSCI1500",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_PITTSBURGH.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_370(
            "CS2010",
            "CSE100",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_SAN_DIEGO.toValue(),
            "Semester 1"),
        MAPPING_371(
            "CS2107",
            "COMP2216",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_SOUTHAMPTON.toValue(),
            "Semester 2"),
        MAPPING_372(
            "CS2103",
            "CSCI310",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_SOUTHERN_CALIFORNIA.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_373(
            "CS2105",
            "CSCI450",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_SOUTHERN_CALIFORNIA.toValue(),
            "Semester 1"),
        MAPPING_374(
            "BT4221",
            "DSCI550",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_SOUTHERN_CALIFORNIA.toValue(),
            "Semester 2"),
        MAPPING_375(
            "CS5331",
            "ITP425",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_SOUTHERN_CALIFORNIA.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_376(
            "CS2105",
            "CS3102",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_ST_ANDREWS.toValue(),
            "Semester 1"),
        MAPPING_377(
            "BT4221",
            "ID5059",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_ST_ANDREWS.toValue(),
            "Semester 2"),
        MAPPING_378(
            "CS3230",
            "DA3004",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_STOCKHOLM.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_379(
            "IS4152",
            "IB447C",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_STOCKHOLM.toValue(),
            "Semester 1"),
        MAPPING_380(
            "IS3251",
            "IB577C",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_STOCKHOLM.toValue(),
            "Semester 2"),
        MAPPING_381(
            "IS4242",
            "ML462C",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_STOCKHOLM.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_382(
            "IS4261",
            "ML482N",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_STOCKHOLM.toValue(),
            "Semester 1"),
        MAPPING_383(
            "CS2103",
            "CSC301H1",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_TORONTO.toValue(),
            "Semester 2"),
        MAPPING_384(
            "CS4236",
            "CSC322H5",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_TORONTO.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_385(
            "CS2105",
            "CSC358H1",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_TORONTO.toValue(),
            "Semester 1"),
        MAPPING_386(
            "CS3230",
            "CSC373H1",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_TORONTO.toValue(),
            "Semester 2"),
        MAPPING_387(
            "CS2103",
            "CSC444H1",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_TORONTO.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_388(
            "CS4212",
            "CSC488",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_TORONTO.toValue(),
            "Semester 1"),
        MAPPING_389(
            "BT2101",
            "ECO375H1",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_TORONTO.toValue(),
            "Semester 2"),
        MAPPING_390(
            "IS4226",
            "RSM434",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_TORONTO.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_391(
            "CS3263",
            "CS4710",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_VIRGINIA.toValue(),
            "Semester 1"),
        MAPPING_392(
            "IS4236",
            "CS4740",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_VIRGINIA.toValue(),
            "Semester 2"),
        MAPPING_393(
            "CS4211",
            "CS6888",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_VIRGINIA.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_394(
            "CS3244",
            "STAT4630",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_VIRGINIA.toValue(),
            "Semester 1"),
        MAPPING_395(
            "CS2040",
            "BME122",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_WATERLOO.toValue(),
            "Semester 2"),
        MAPPING_396(
            "CS2100",
            "CS106",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_WATERLOO.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_397(
            "CS3219",
            "CS247",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_WATERLOO.toValue(),
            "Semester 1"),
        MAPPING_398(
            "CS3219",
            "CS446",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_WATERLOO.toValue(),
            "Semester 2"),
        MAPPING_399(
            "CS3240",
            "CS449",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_WATERLOO.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_400(
            "CS2105",
            "CS456",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_WATERLOO.toValue(),
            "Semester 1"),
        MAPPING_401(
            "BT4212",
            "MSCI541",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_WATERLOO.toValue(),
            "Semester 2"),
        MAPPING_402(
            "CS2106",
            "SE350",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_WATERLOO.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_403(
            "CS3244",
            "MAT003",
            SampleUniversities.SampleUniversityName.UNIVERSITY_OF_ZURICH.toValue(),
            "Semester 1"),
        MAPPING_404(
            "CS3103",
            "CS5SD808",
            SampleUniversities.SampleUniversityName.UPPSALA_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_405(
            "CS1010",
            "BETAB1PYT",
            SampleUniversities.SampleUniversityName.UTRECHT_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_406(
            "CS4235",
            "INFOGA",
            SampleUniversities.SampleUniversityName.UTRECHT_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_407(
            "CS2040C",
            "CS2201",
            SampleUniversities.SampleUniversityName.VANDERBILT_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_408(
            "CS2106",
            "NWEN301",
            SampleUniversities.SampleUniversityName.VICTORIA_UNIVERSITY_OF_WELLINGTON.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_409(
            "CS2103",
            "SWEN221",
            SampleUniversities.SampleUniversityName.VICTORIA_UNIVERSITY_OF_WELLINGTON.toValue(),
            "Semester 1"),
        MAPPING_410(
            "CS1010",
            "INFP11ZW",
            SampleUniversities.SampleUniversityName.WASEDA_UNIVERSITY.toValue(),
            "Semester 2"),
        MAPPING_411(
            "CS2102",
            "CS3319A",
            SampleUniversities.SampleUniversityName.WESTERN_UNIVERSITY.toValue(),
            "Semester 1 & Semester 2"),
        MAPPING_412(
            "CS2106",
            "SE3313A",
            SampleUniversities.SampleUniversityName.WESTERN_UNIVERSITY.toValue(),
            "Semester 1"),
        MAPPING_413(
            "CS3244",
            "STA3142",
            SampleUniversities.SampleUniversityName.YONSEI_UNIVERSITY.toValue(),
            "Semester 2");

        private final String localCode;
        private final String partnerCode;
        private final String universityName;
        private final String information;

        SampleMapping(String localCode, String partnerCode, String universityName, String information) {
            this.localCode = localCode;
            this.partnerCode = partnerCode;
            this.universityName = universityName;
            this.information = information;
        }

        /**
         * Gets the string representation of the mapping local code.
         *
         * @return The string representation of the mapping local code.
         */
        public String getLocalCode() {
            return this.localCode;
        }

        /**
         * Gets the string representation of the mapping partner code.
         *
         * @return The string representation of the mapping partner code.
         */
        public String getPartnerCode() {
            return this.partnerCode;
        }

        /**
         * Gets the string representation of the mapping university name.
         *
         * @return The string representation of the mapping university name.
         */
        public String getUniversityName() {
            return this.universityName;
        }

        /**
         * Gets the string representation of the mapping information.
         *
         * @return The string representation of the mapping information.
         */
        public String getInformation() {
            return this.information;
        }
    }

    /**
     * Returns an array of sample mappings.
     *
     * @return An array of sample {@code Mapping} objects.
     */
    public static Mapping[] getSampleMappings() {
        return Arrays.stream(SampleMapping.values())
                     .map(data -> new Mapping(
                         new LocalCode(data.getLocalCode()),
                         new UniversityName(data.getUniversityName()),
                         new PartnerCode(data.getPartnerCode()),
                         new MappingMiscInformation(data.getInformation())))
                     .toArray(Mapping[]::new);
    }
}
