import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Mapper;

public class StringIndexMapper extends Mapper<LongWritable, Text, Text, Text> {
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		@SuppressWarnings("resource")
			int fileIdx = 0;
			String[] head = value.toString().split(",");
			String fileId = head[0].trim();
			Text word;
			Text wrapper;
//			while (token.hasNext()) {
//				wrapper = new Text(PositionWrapper.serialize(fileId, fileIdx));
//				String s = StringUtils.cleanWord(token.next().trim());
//				if (s.length() > 2) {
//					word = new Text(s);
//					context.write(word, wrapper);  // word, (fileId, fileIdx)
//				}
//				fileIdx += 1;
//			}
			String[] contents = head[3].split("\\s+");
			for (String next:contents) {
				wrapper = new Text(PositionWrapper.serialize(fileId, fileIdx));
				String s = StringUtils.cleanWord(next.trim());
				if (s.length() > 2) {
					word = new Text(s);
					context.write(word, wrapper);  // word, (fileId, fileIdx)
				}
				fileIdx += 1;
			}
	}
}