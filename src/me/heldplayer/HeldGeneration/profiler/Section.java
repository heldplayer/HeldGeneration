package me.heldplayer.HeldGeneration.profiler;

import java.util.Map;
import java.util.TreeMap;

public class Section {
	public final String name;
	public Section parent;
	public Map<String, Section> childSections;
	public long startNanos = 0L;
	public long totalNanos = 0L;
	public int calls = 0;

	protected Section(String name) {
		this.startNanos = System.currentTimeMillis();
		this.name = name;
		this.childSections = new TreeMap<String, Section>();
	}
}
