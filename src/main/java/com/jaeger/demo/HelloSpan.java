package com.jaeger.demo;

import io.jaegertracing.Configuration;
import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.Tracer.SpanBuilder;
import io.opentracing.util.GlobalTracer;

public class HelloSpan {

	private final Tracer tracer;

	public HelloSpan(Tracer tracer) {
		this.tracer = tracer;
	}

	public static void main(String[] args) {

		if (args.length != 1) {
			throw new IllegalArgumentException("One Arguement Is Mandatory !!!");
		}
		String helloServiceArgs = args[0];

		Tracer tracer = initTracer("hello-world-service");

		new HelloSpan(tracer).sayHello(helloServiceArgs);
	}

	private static Tracer initTracer(String string) {
		// TODO Auto-generated method stub

		SamplerConfiguration samplerConfig = SamplerConfiguration.fromEnv().withType(ConstSampler.TYPE).withParam(1);

		ReporterConfiguration reporterConfig = ReporterConfiguration.fromEnv().withLogSpans(true);

		Configuration config = new Configuration("helloWorld").withSampler(samplerConfig).withReporter(reporterConfig);

		return config.getTracer();
	}

	private void sayHello(String helloServiceArgs) {
		// TODO Auto-generated method stub
		Span buildSpan = this.tracer.buildSpan("hello-Span").start();
		String format = String.format("Hello, %s !", helloServiceArgs);
		System.out.println(format);
		buildSpan.finish();
	}

}
