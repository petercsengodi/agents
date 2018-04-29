package hu.csega.common;

public class BitAssembler {

	private byte[] byteArray;
	private int position;
	private int bitNumber;

	public byte[] getByteArray() {
		return byteArray;
	}

	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
		this.bitNumber = 0;
	}

	public int getBitNumber() {
		return bitNumber;
	}

	public void setBitNumber(int bitNumber) {
		this.bitNumber = bitNumber;
	}

	public void setBitsAndSlide(int value, int numberOfBitsTillTheEnd) {
		int pos = (1 << (numberOfBitsTillTheEnd - 1));
		while(pos > 0) {
			setBitAndSlide((value & pos) > 0);
			pos = pos >> 1;
		}
	}

	public void setBitAndSlide(boolean bit) {
		if(position >= byteArray.length)
			throw new IllegalStateException("No such byte: " + position);

		setBit(bitNumber, bit);
		bitNumber++;

		if(bitNumber >= 8) {
			position++;
			bitNumber = 0;
		}
	}

	private void setBit(int i, boolean bit) {
		switch(i) {
		case 0:
			setFirstBit(bit);
			break;
		case 1:
			setSecondBit(bit);
			break;
		case 2:
			setThirdBit(bit);
			break;
		case 3:
			setFourthBit(bit);
			break;
		case 4:
			setFifthBit(bit);
			break;
		case 5:
			setSixthBit(bit);
			break;
		case 6:
			setSeventhBit(bit);
			break;
		case 7:
			setEigthBit(bit);
			break;
		default:
			throw new RuntimeException("A byte doesn't have a bit: " + i);
		}
	}

	private void setFirstBit(boolean bit) {
		if(bit)
			byteArray[position] |= 0x80;
		else
			byteArray[position] &= 0x7F;
	}

	private void setSecondBit(boolean bit) {
		if(bit)
			byteArray[position] |= 0x40;
		else
			byteArray[position] &= 0xBF;
	}

	private void setThirdBit(boolean bit) {
		if(bit)
			byteArray[position] |= 0x20;
		else
			byteArray[position] &= 0xDF;
	}

	private void setFourthBit(boolean bit) {
		if(bit)
			byteArray[position] |= 0x10;
		else
			byteArray[position] &= 0xEF;
	}

	private void setFifthBit(boolean bit) {
		if(bit)
			byteArray[position] |= 0x08;
		else
			byteArray[position] &= 0xF7;
	}

	private void setSixthBit(boolean bit) {
		if(bit)
			byteArray[position] |= 0x04;
		else
			byteArray[position] &= 0xFB;
	}

	private void setSeventhBit(boolean bit) {
		if(bit)
			byteArray[position] |= 0x02;
		else
			byteArray[position] &= 0xFD;
	}

	private void setEigthBit(boolean bit) {
		if(bit)
			byteArray[position] |= 0x01;
		else
			byteArray[position] &= 0xFE;
	}

}
