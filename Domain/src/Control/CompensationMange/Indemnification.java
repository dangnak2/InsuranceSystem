package Control.CompensationMange;

public class Indemnification {

    public enum AccidentSubjectIndemnification {
        eSubject1("보험 가입자", true),
        eSubject3("전쟁 및 테러", false),
        eSubject4("자연 재해", false);


        private final String explanation;
        private final boolean judgment;

        AccidentSubjectIndemnification(String explanation, boolean judgment) {
            this.explanation = explanation;
            this.judgment = judgment;
        }

        public String getExplanation() {
            return explanation;
        }

        public boolean isJudgment() {
            return judgment;
        }
    }

    public enum CarAccidentCauseIndemnification{
        eCarCause1("경미한 과실(실수)로 발생했을 경우", true),
        eCarCause2("상대방의 실수로 발생 했을 경우", true),
        eCarCause3("운전자가 보험가입자가 아닌 경우", false),
        eCarCause4("보험 가입자가 고의로 사고를 일으킨 경우", false),
        eCarCause5("보험 가입자가 자동차를 이용한 영업 행위(유사운송)였을 경우", false),
        eCarCause6("보험 가입자가 시험용 또는 경기용으로 운행 했을 경우", false),
        eCarCause7("보험 가입자가 음주 및 무면허 운정 행위인 경우", false);

        private final String explanation;
        private final boolean judgment;

        CarAccidentCauseIndemnification(String explanation, boolean judgment) {
            this.explanation = explanation;
            this.judgment = judgment;
        }

        public String getExplanation() {
            return explanation;
        }

        public boolean isJudgment() {
            return judgment;
        }
    }


    public enum SeaAccidentCauseIndemnification{
        eSeaCause1("경미한 과실(실수)로 발생했을 경우", true),
        eSeaCause2("보험 가입자가 고의적 비행에 기인한 멸실, 손상으로 발생 했을 경우", false),
        eSeaCause3("원자력 및 핵의 분열 혹은 융합으로 인해 방사능으로 발생 했을 경우", false),
        eSeaCause4("선박 또는 부선의 불내항으로 인해 발생 했을 경우", false),
        eSeaCause5("화물 적재의 부적합으로 인해 발생 했을 경우", false);

        private final String explanation;
        private final boolean judgment;

        SeaAccidentCauseIndemnification(String explanation, boolean judgment) {
            this.explanation = explanation;
            this.judgment = judgment;
        }

        public String getExplanation() {
            return explanation;
        }

        public boolean isJudgment() {
            return judgment;
        }
    }

    public enum FireAccidentCauseIndemnification{
        eFireCause1("경미한 과실(실수)로 발생했을 경우", true),
        eFireCause2("불 혹은 폭발로 인한 사고일 경우", false),
        eFireCause3("친족 혹은 고용인의 고의인 경우", false),
        eFireCause4("전기기기 또는 전기적 사고로 발생 했을 경우", false);

        private final String explanation;
        private final boolean judgment;

        FireAccidentCauseIndemnification(String explanation, boolean judgment) {
            this.explanation = explanation;
            this.judgment = judgment;
        }

        public String getExplanation() {
            return explanation;
        }

        public boolean isJudgment() {
            return judgment;
        }
    }




}
