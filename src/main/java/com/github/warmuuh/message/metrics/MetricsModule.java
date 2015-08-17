package com.github.warmuuh.message.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;

import wrm.hardwire.Module;

@Module(external={
		MetricRegistry.class,
		HealthCheckRegistry.class
})
public class MetricsModule extends MetricsModuleBase {
	@Override
	protected MetricRegistry createMetricRegistry() {
		return new MetricRegistry();
	}

	@Override
	protected HealthCheckRegistry createHealthCheckRegistry() {
		return new HealthCheckRegistry();
	}
}
