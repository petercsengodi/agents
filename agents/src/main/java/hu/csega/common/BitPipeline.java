package hu.csega.common;

public class BitPipeline {

	private int capacity;
	private int values[];
	private int c = 0;
	private int numberOfBytes = 0;
	private int position = -1;

	public BitPipeline() {
		this.capacity = 20;
		this.values = new int[this.capacity];
	}

	public BitPipeline(int capacity) {
		this.capacity = capacity;
		this.values = new int[this.capacity];
	}

	public void clear() {
		this.position = -1;
		this.c = 0;
		this.numberOfBytes = 0;
	}

	public void set(int i) {
		_set(i & 0xFF);
	}

	public void set(byte b) {
		_set(b & 0xFF);
	}

	public void set(byte[] bytes, int offset, int count) {
		if(count > capacity) {
			throw new IllegalStateException("Too much data, can only accept " + capacity + " bytes!");
		}

		for(int i = 0; i < count; i++) {
			this.values[i] = (bytes[i + offset] & 0xFF);
		}

		this.position = -1;
		this.c = 0;
		this.numberOfBytes = count;
	}

	private void _set(int v) {
		this.position = -1;
		this.values[0] = v;
		this.c = 0;
		this.numberOfBytes = 1;
	}

	public void add(byte[] bytes, int offset, int count) {
		int end = offset + count;
		for(int i = offset; i < end; i++) {
			_add(bytes[i]);
		}
	}

	public void add(byte b) {
		_add(b & 0xFF);
	}

	public void add(int i) {
		_add(i & 0xFF);
	}

	private void _add(int v) {
		if(numberOfBytes >= capacity) {
			throw new IllegalStateException("Too much data, can only accept " + capacity + " bytes!");
		}

		this.numberOfBytes++;
		this.values[(this.position + numberOfBytes) % capacity] = v;
	}

	public boolean readBoolean() {
		if(c < 1 && numberOfBytes < 1) {
			throw new IllegalStateException("Out of bits!");
		}

		if(c < 1) {
			numberOfBytes --;
			position = (position + 1) % capacity;
			c = 8;
		}

		int j = values[position];
		boolean ret = ((j & 128) == 128);
		values[position] = (j << 1);
		c--;
		return ret;
	}

	public int readBits(int count) {
		int ret = 0;

		for(int i = 0; i < count; i++) {
			ret = ret << 1;
			if(readBoolean()) {
				ret++;
			}
		}

		return ret;
	}
}
