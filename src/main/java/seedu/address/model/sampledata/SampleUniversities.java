package seedu.address.model.sampledata;

import java.util.Arrays;

import seedu.address.model.university.University;
import seedu.address.model.university.UniversityName;

/**
 * A utility class that provides sample universities for seeding the application.
 */
public class SampleUniversities {
    /**
     * The `SampleUniversityName` enum represents sample university names.
     */
    public enum SampleUniversityName {
        THE_HONG_KONG_POLYTECHNIC_UNIVERSITY("The Hong Kong Polytechnic University"),
        HONG_KONG_UNIVERSITY_OF_SCIENCE_AND_TECHNOLOGY("Hong Kong University of Science & Technology"),
        CITY_UNIVERSITY_OF_HONG_KONG("City University of Hong Kong"),
        THE_UNIVERSITY_OF_HONG_KONG("The University of Hong Kong"),
        THE_CHINESE_UNIVERSITY_OF_HONG_KONG("The Chinese University of Hong Kong"),
        BUDAPEST_UNIVERSITY_OF_TECHNOLOGY_AND_ECONOMICS("Budapest University of Technology and Economics"),
        UNIVERSITY_COLLEGE_CORK_IRELAND("University College Cork, Ireland"),
        TRINITY_COLLEGE_DUBLIN("Trinity College Dublin"),
        UNIVERSITY_OF_COMMERCE_LUIGI_BOCCONI_MILAN("University of Commerce 'Luigi Bocconi' Milan"),
        TOHOKU_UNIVERSITY("Tohoku University"),
        KYUSHU_UNIVERSITY("Kyushu University"),
        WASEDA_UNIVERSITY("Waseda University"),
        TOKYO_INSTITUTE_OF_TECHNOLOGY("Tokyo Institute of Technology"),
        THE_UNIVERSITY_OF_TOKYO("The University of Tokyo"),
        OSAKA_UNIVERSITY("Osaka University"),
        KYOTO_UNIVERSITY("Kyoto University"),
        KEIO_UNIVERSITY("Keio University"),
        VILNIUS_UNIVERSITY("Vilnius University"),
        UTRECHT_UNIVERSITY("Utrecht University"),
        EINDHOVEN_UNIVERSITY_OF_TECHNOLOGY("Eindhoven University of Technology"),
        DELFT_UNIVERSITY_OF_TECHNOLOGY("Delft University of Technology"),
        UNIVERSITY_OF_AMSTERDAM("University of Amsterdam"),
        VICTORIA_UNIVERSITY_OF_WELLINGTON("Victoria University of Wellington"),
        UNIVERSITY_OF_OTAGO("University of Otago"),
        THE_UNIVERSITY_OF_AUCKLAND("The University of Auckland"),
        NORWEGIAN_UNIVERSITY_OF_SCIENCE_AND_TECHNOLOGY("Norwegian University of Science & Technology"),
        NORWEGIAN_SCHOOL_OF_ECONOMICS("Norwegian School of Economics"),
        WARSAW_UNIVERSITY_OF_TECHNOLOGY("Warsaw University of Technology"),
        SINGAPORE_MANAGEMENT_UNIVERSITY("Singapore Management University"),
        NANYANG_TECHNOLOGICAL_UNIVERSITY("Nanyang Technological University"),
        HANYANG_UNIVERSITY("Hanyang University"),
        SUNGKYUNKWAN_UNIVERSITY("Sungkyunkwan University"),
        EWHA_WOMENS_UNIVERSITY_SEOUL("Ewha Women's University,Seoul"),
        KOREA_UNIVERSITY("Korea University"),
        KOREA_ADVANCED_INST_OF_SCI_AND_TECH("Korea Advanced Inst of Sci & Tech"),
        YONSEI_UNIVERSITY("Yonsei University"),
        SEOUL_NATIONAL_UNIVERSITY("Seoul National University"),
        CHALMERS_UNIVERSITY_OF_TECHNOLOGY("Chalmers University of Technology"),
        LINKOPING_UNIVERSITY("Linkoping University"),
        UPPSALA_UNIVERSITY("Uppsala University"),
        UNIVERSITY_OF_STOCKHOLM("University of Stockholm"),
        KTH_ROYAL_INSTITUTE_OF_TECHNOLOGY("KTH Royal Institute of Technology"),
        LUND_UNIVERSITY("Lund University"),
        ETH_ZURICH("ETH Zurich"),
        UNIVERSITY_OF_ZURICH("University of Zurich"),
        SWISS_FEDERAL_INSTITUTE_OF_TECHNOLOGY_LAUSANNE("Swiss Federal Institute of Technology Lausanne"),
        NATIONAL_CHIAO_TUNG_UNIVERSITY("National Chiao Tung University"),
        NATIONAL_TSING_HUA_UNIVERSITY("National Tsing Hua University"),
        NATIONAL_CHENG_KUNG_UNIVERSITY("National Cheng Kung University"),
        NATIONAL_TAIWAN_UNIVERSITY("National Taiwan University"),
        THE_UNIVERSITY_OF_WESTERN_AUSTRALIA("The University of Western Australia"),
        THE_UNIVERSITY_OF_SYDNEY("The University of Sydney"),
        THE_UNIVERSITY_OF_QUEENSLAND("The University of Queensland"),
        THE_UNIVERSITY_OF_NEW_SOUTH_WALES("The University of New South Wales"),
        MONASH_UNIVERSITY("Monash University"),
        THE_UNIVERSITY_OF_MELBOURNE("The University of Melbourne"),
        LATROBE_UNIVERSITY("LaTrobe University"),
        THE_UNIVERSITY_OF_ADELAIDE("The University of Adelaide"),
        THE_AUSTRALIAN_NATIONAL_UNIVERSITY("The Australian National University"),
        KATHOLIEKE_UNIVERSITEIT_LEUVEN_KU_LEUVEN("Katholieke Universiteit Leuven (KU Leuven)"),
        UNIVERSITY_OF_WATERLOO("University of Waterloo"),
        WESTERN_UNIVERSITY("Western University"),
        UNIVERSITY_OF_VICTORIA_BRITISH_COLUMBIA("University of Victoria, British Columbia"),
        UNIVERSITY_OF_TORONTO("University of Toronto"),
        SIMON_FRASER_UNIVERSITY("Simon Fraser University"),
        QUEENS_UNIVERSITY_AT_KINGSTON("Queen's University at Kingston"),
        UNIVERSITY_OF_OTTAWA("University of Ottawa"),
        MCGILL_UNIVERSITY("McGill University"),
        DALHOUSIE_UNIVERSITY("Dalhousie University"),
        UNIVERSITY_OF_CALGARY("University of Calgary"),
        CONCORDIA_UNIVERSITY("Concordia University"),
        THE_UNIVERSITY_OF_BRITISH_COLUMBIA("The University of British Columbia"),
        UNIVERSITY_OF_ALBERTA("University of Alberta"),
        BEIHANG_UNIVERSITY("Beihang University"),
        RENMIN_UNIVERSITY_OF_CHINA("Renmin University of China"),
        ZHEJIANG_UNIVERSITY("Zhejiang University"),
        TSINGHUA_UNIVERSITY("Tsinghua University"),
        PEKING_UNIVERSITY("Peking University"),
        SHANGHAI_JIAO_TONG_UNIVERSITY("Shanghai Jiao Tong University"),
        FUDAN_UNIVERSITY("Fudan University"),
        UNIVERSITY_OF_COPENHAGEN("University of Copenhagen"),
        AARHUS_UNIVERSITY("Aarhus University"),
        TECHNICAL_UNIVERSITY_OF_DENMARK("Technical University of Denmark"),
        UNIVERSITY_OF_HELSINKI("University of Helsinki"),
        RWTH_AACHEN_UNIVERSITY("RWTH Aachen University"),
        HAMBURG_UNIVERSITY_OF_TECHNOLOGY_TUHH("Hamburg University of Technology (TUHH)"),
        UNIVERSITY_OF_KONSTANZ("University of Konstanz"),
        UNIVERSITY_OF_APPLIED_SCIENCES_ESSLINGEN("University of Applied Sciences Esslingen"),
        WHU_OTTO_BEISHEIM_SCHOOL_OF_MANAGEMENT("WHU - Otto Beisheim School of Management"),
        UNIVERSITY_OF_MANNHEIM("University of Mannheim"),
        TECHNICAL_UNIVERSITY_OF_MUNICH("Technical University of Munich"),
        KARLSRUHE_INSTITUTE_OF_TECHNOLOGY("Karlsruhe Institute of Technology"),
        UNIVERSITY_OF_KAISERSLAUTERN_FRG("University of Kaiserslautern, FRG"),
        HEIDELBERG_UNIVERSITY_RUPR_KARLS_UNI_HEIDELBERG("Heidelberg University (Rupr-Karls-Uni Heidelberg)"),
        TECHNICAL_UNIVERSITY_OF_DARMSTADT("Technical University of Darmstadt"),
        CHULALONGKORN_UNIVERSITY("Chulalongkorn University"),
        BOGAZICI_UNIVERSITY_TURKEY("Bogazici University, Turkey"),
        IMPERIAL_COLLEGE_LONDON("Imperial College London"),
        UNIVERSITY_COLLEGE_LONDON_UNIVERSITY_OF_LONDON("University College London, University of London"),
        KINGS_COLLEGE_LONDON("King's College London"),
        LONDON_SCHOOL_OF_ECONOMICS_AND_AND_POLITICAL_SCIENCE("London School of Economics & Political Science"),
        THE_UNIVERSITY_OF_MANCHESTER("The University of Manchester"),
        UNIVERSITY_OF_YORK("University of York"),
        UNIVERSITY_OF_WARWICK("University of Warwick"),
        UNIVERSITY_COLLEGE_LONDON("University College London"),
        THE_UNIVERSITY_OF_SHEFFIELD("The University of Sheffield"),
        UNIVERSITY_OF_SOUTHAMPTON("University of Southampton"),
        UNIVERSITY_OF_OXFORD("University of Oxford"),
        THE_UNIVERSITY_OF_NOTTINGHAM("The University of Nottingham"),
        UNIVERSITY_OF_NEWCASTLE_UPON_TYNE("University of Newcastle upon Tyne"),
        UNIVERSITY_OF_LIVERPOOL("University of Liverpool"),
        UNIVERSITY_OF_LEEDS("University of Leeds"),
        UNIVERSITY_OF_GLASGOW("University of Glasgow"),
        THE_UNIVERSITY_OF_EDINBURGH("The University of Edinburgh"),
        UNIVERSITY_OF_DUNDEE("University of Dundee"),
        UNIVERSITY_OF_BRISTOL("University of Bristol"),
        UNIVERSITY_OF_BIRMINGHAM("University of Birmingham"),
        UNIVERSITY_OF_BATH("University of Bath"),
        BOSTON_COLLEGE_MASSACHUSETTS("Boston College, Massachusetts"),
        IOWA_STATE_UNIVERSITY("Iowa State University"),
        UNIVERSITY_OF_SAN_DIEGO("University of San Diego"),
        UNIVERSITY_OF_WISCONSIN_MADISON("University of Wisconsin-Madison"),
        UNIVERSITY_OF_WASHINGTON_SEATTLE("University of Washington, Seattle"),
        UNIVERSITY_OF_VIRGINIA("University of Virginia"),
        VANDERBILT_UNIVERSITY("Vanderbilt University"),
        TEXAS_A_AND_M_UNIVERSITY_COLLEGE_STATION_TEXAS("Texas A & M University,College Station,Texas"),
        THE_UNIVERSITY_OF_TEXAS_AT_AUSTIN("The University of Texas at Austin"),
        STANFORD_UNIVERSITY("Stanford University"),
        UNIVERSITY_OF_SOUTHERN_CALIFORNIA("University of Southern California"),
        PURDUE_UNIVERSITY("Purdue University"),
        UNIVERSITY_OF_PITTSBURGH("University of Pittsburgh"),
        PENNSYLVANIA_STATE_UNIVERSITY("Pennsylvania State University"),
        UNIVERSITY_OF_PENNSYLVANIA("University of Pennsylvania"),
        NORTHWESTERN_UNIVERSITY("Northwestern University"),
        UNIVERSITY_OF_NORTH_CAROLINA_CHAPEL_HILL("University of North Carolina, Chapel Hill"),
        UNIVERSITY_OF_MINNESOTA("University of Minnesota"),
        UNIVERSITY_OF_MICHIGAN_ANN_ARBOR("University of Michigan, Ann Arbor"),
        UNIVERSITY_OF_MIAMI("University of Miami"),
        UNIVERSITY_OF_MARYLAND("University of Maryland"),
        JOHNS_HOPKINS_UNIVERSITY("Johns Hopkins University"),
        INDIANA_UNIVERSITY_BLOOMINGTON("Indiana University, Bloomington"),
        UNIVERSITY_OF_ILLINOIS_URBANA_CHAMPAIGN("University of Illinois, Urbana-Champaign"),
        UNIVERSITY_OF_GEORGIA("University of Georgia"),
        GEORGETOWN_UNIVERSITY_WASHINGTON_DC("Georgetown University, Washington D.C."),
        GEORGIA_INSTITUTE_OF_TECHNOLOGY("Georgia Institute of Technology"),
        THE_GEORGE_WASHINGTON_UNIVERSITY("The George Washington University"),
        DUKE_UNIVERSITY("Duke University"),
        UNIVERSITY_OF_CONNECTICUT("University of Connecticut"),
        THE_COLLEGE_OF_WILLIAM_AND_MARY("The College of William & Mary"),
        COLUMBIA_UNIVERSITY("Columbia University"),
        CASE_WESTERN_RESERVE_UNIVERSITY("Case Western Reserve University"),
        CARNEGIE_MELLON_UNIVERSITY("Carnegie Mellon University"),
        UNIVERSITY_OF_CALIFORNIA_RIVERSIDE("University of California, Riverside"),
        UNIVERSITY_OF_CALIFORNIA_LOS_ANGELES("University of California, Los Angeles"),
        UNIVERSITY_OF_CALIFORNIA_SAN_DIEGO("University of California, San Diego"),
        UNIVERSITY_OF_CALIFORNIA_SANTA_CRUZ("University of California, Santa Cruz"),
        UNIVERSITY_OF_CALIFORNIA_SANTA_BARBARA("University of California, Santa Barbara"),
        UNIVERSITY_OF_CALIFORNIA_IRVINE("University of California, Irvine"),
        UNIVERSITY_OF_CALIFORNIA_DAVIS("University of California, Davis"),
        UNIVERSITY_OF_CALIFORNIA("University of California"),
        BRANDEIS_UNIVERSITY("Brandeis University"),
        BOSTON_UNIVERSITY("Boston University"),
        ARIZONA_STATE_UNIVERSITY("Arizona State University"),
        THE_UNIVERSITY_OF_ARIZONA("The University of Arizona"),
        INST_TECH_Y_DE_ESTUDIOS_SUPERIORES_DE_MONTERREY("Inst Tech y de Estudios Superiores de Monterrey"),
        POHANG_UNIVERSITY_OF_SCIENCE_AND_TECHNOLOGY("Pohang University of Science & Technology"),
        UNIVERSITY_OF_GOTEBORG("University of Goteborg"),
        ECOLE_POLYTECHNIQUE_FEDERALE_DE_LAUSANNE("Ecole Polytechnique Federale de Lausanne"),
        UNIVERSITY_OF_GENEVA("University of Geneva"),
        UNIVERSITY_OF_ANTWERP("University of Antwerp"),
        HEC_MONTREAL("HEC Montreal"),
        UNIVERSITY_OF_GUELPH("University of Guelph"),
        TALLINN_UNIVERSITY_OF_TECHNOLOGY("Tallinn University of Technology"),
        LUDWIG_MAXIMILIANS_UNIVERSITY_OF_MUNICH("Ludwig-Maximilians-University of Munich"),
        TULANE_UNIVERSITY("Tulane University"),
        AACHEN_UNIVERSITY_OF_TECHNOLOGY("Aachen University of Technology"),
        TILBURG_UNIVERSITY("Tilburg University"),
        CRACOW_UNIVERSITY_OF_TECHNOLOGY("Cracow University of Technology"),
        BILKENT_UNIVERSITY("Bilkent University"),
        UNIVERSITY_OF_CALIFORNIA_MERCED("University of California, Merced"),
        RICHARD_IVEY_SCH_OF_BIZ_UNIV_OF_WESTERN_ONTARIO("Richard Ivey Sch of Biz, Univ of Western Ontario"),
        UNIVERSITY_OF_OSLO("University of Oslo"),
        UNIVERSITY_COLLEGE_DUBLIN("University College Dublin"),
        AALTO_UNIVERSITY("Aalto University"),
        UNIVERSITY_OF_ST_ANDREWS("University of St Andrews"),
        SINGAPORE_UNIVERSITY_OF_TECHNOLOGY_AND_DESIGN("Singapore University of Technology and Design"),
        UNIVERSITY_OF_COLORADO_BOULDER("University of Colorado Boulder"),
        CHANG_GUNG_UNIVERSITY("Chang Gung University"),
        REICHMAN_UNIVERSITY("Reichman University"),
        CORNELL_UNIV_COLL_OF_AGRICULTURE_AND_AND_LIFE_SCIENCES("Cornell Univ Coll of Agriculture & Life Sciences"),
        CORNELL_UNIV_COLL_OF_HUMAN_ECOLOGY("Cornell Univ Coll of Human Ecology"),
        UNIVERSITY_OF_GOTHENBURG("University of Gothenburg"),
        UNIVERSITY_OF_TORONTO_SCARBOROUGH("University of Toronto (Scarborough)"),
        COLGATE_UNIVERSITY("Colgate University"),
        UNIVERSITE_GRENOBLE_ALPES("Universite Grenoble Alpes"),
        UNIVERSITAT_AUTONOMA_DE_BARCELONA("Universitat Autonoma de Barcelona"),
        UNIVERSITY_OF_ICELAND("University of Iceland"),
        NATIONAL_YANG_MING_CHIAO_TUNG_UNIVERSITY("National Yang Ming Chiao Tung University");

        private final String value;

        /**
         * Constructs a `SampleUniversityName` enum with the given formatted value.
         *
         * @param value The formatted string representation of the university name.
         */
        SampleUniversityName(String value) {
            this.value = value;
        }

        /**
         * Gets the formatted string representation of the `SampleUniversityName`.
         *
         * @return The formatted string representation of the `SampleUniversityName`.
         */
        public String toValue() {
            return this.value;
        }
    }

    /**
     * Returns an array of sample universities.
     *
     * @return An array of sample {@code University} objects.
     */
    public static University[] getSampleUniversities() {
        return Arrays.stream(SampleUniversityName.values())
                     .map(data -> new UniversityName(data.toValue()))
                     .map(University::new)
                     .toArray(University[]::new);
    }
}
