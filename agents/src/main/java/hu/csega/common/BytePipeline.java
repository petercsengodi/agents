package hu.csega.common;

public class BytePipeline {

	private byte[] bytes;
	private int length;
	private int position;

	public BytePipeline(byte[] bytes) {
		this.bytes = bytes;
		this.length = bytes.length;
		this.position = 0;
	}

	public boolean hasNext() {
		return position < length;
	}

	public int next() {
		return bytes[position++];
	}
}
