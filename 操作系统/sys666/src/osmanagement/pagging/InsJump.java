package osmanagement.pagging;

class InsJump extends Instruction{
	final int JUMP_PAGE = 4;
	final int JUMP_NUM = Paging.IperP * JUMP_PAGE;
	String cGetString(int n){
		return "����������" + n + "��";
	}
	int cComputePC(int currentPc) {
		return currentPc + num;
	}
	Instruction create() {
		Instruction product = new InsJump();
		product.num = randNum.nextInt(JUMP_NUM);
		return product;
	}
}
