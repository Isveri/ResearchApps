//package pl.piomin.services.grpc.account.service;
//
//import grafana.Api;
//import grafana.GrafanaQueryAPIGrpc;
//import io.grpc.stub.StreamObserver;
//import net.devh.boot.grpc.server.service.GrpcService;
//
//import java.util.Map;
//
//@GrpcService
//public class GrafanaQueryAPIService extends GrafanaQueryAPIGrpc.GrafanaQueryAPIImplBase {
//
//    //TODO zostal tu wrzucony przykladowe dzialnie metod. Trzeba je przerobic aby odzwierciedlaly dzialanie aplikacji
//    @Override
//    public void listDimensionKeys(Api.ListDimensionKeysRequest request, StreamObserver<Api.ListDimensionKeysResponse> responseObserver) {
//        Api.ListDimensionKeysResponse dimensions = Api.ListDimensionKeysResponse.newBuilder()
//                .addResults(Api.ListDimensionKeysResponse.Result.newBuilder()
//                        .setKey("room")
//                        .setDescription("The selected room"))
//                .build();
//        responseObserver.onNext(dimensions);
//        responseObserver.onCompleted();
//    }
//
//    @Override
//    public void listDimensionValues(Api.ListDimensionValuesRequest request, StreamObserver<Api.ListDimensionValuesResponse> responseObserver) {
//        String filter = request.getFilter();
//        Api.ListDimensionValuesResponse.Builder responseBuilder = Api.ListDimensionValuesResponse.newBuilder();
//        for (Map.Entry<String, Sensor> entry : sensors.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue().getDescription();
//            if (filter == null || key.contains(filter) || value.contains(filter)) {
//                responseBuilder.addResults(Api.ListDimensionValuesResponse.Result.newBuilder()
//                        .setValue(key)
//                        .setDescription(value));
//            }
//        }
//        responseObserver.onNext(responseBuilder.build());
//        responseObserver.onCompleted();
//    }
//
//    @Override
//    public void listMetrics(Api.ListMetricsRequest request, StreamObserver<Api.ListMetricsResponse> responseObserver) {
//        if (request.getDimensionsCount() < 1) {
//            return;
//        }
//        String room = request.getDimensions(0).getValue();
//        if (room == null || room.isEmpty()) {
//            return;
//        }
//        Api.ListMetricsResponse.Builder responseBuilder = Api.ListMetricsResponse.newBuilder();
//        responseBuilder.addMetrics(Api.ListMetricsResponse.Metric.newBuilder()
//                .setName(String.format("/%s/temperature", room))
//                .setDescription("Temperature"));
//        responseBuilder.addMetrics(Api.ListMetricsResponse.Metric.newBuilder()
//                .setName(String.format("/%s/humidity", room))
//                .setDescription("Humidity"));
//        responseObserver.onNext(responseBuilder.build());
//        responseObserver.onCompleted();
//    }
//
//    @Override
//    public void getMetricValue(Api.GetMetricValueRequest request, StreamObserver<Api.GetMetricValueResponse> responseObserver) {
//        String metric = request.getMetric();
//        if (metric == null || metric.isEmpty()) {
//            return;
//        }
//        Sensor sensor = sensors.get(metric);
//        if (sensor == null) {
//            responseObserver.onError(new IllegalArgumentException(String.format("metric %s does not exist; please check your configuration", metric)));
//            return;
//        }
//        long timestamp = System.currentTimeMillis() / 1000;
//        double value = sensor.getValueAt(System.currentTimeMillis());
//        Api.GetMetricValueResponse response = Api.GetMetricValueResponse.newBuilder()
//                .setTimestamp(timestamp)
//                .setValue(Api.MetricValue.newBuilder().setDoubleValue(value))
//                .build();
//        responseObserver.onNext(response);
//        responseObserver.onCompleted();
//    }
//
//    @Override
//    public void getMetricHistory(Api.GetMetricHistoryRequest request, StreamObserver<Api.GetMetricHistoryResponse> responseObserver) {
//        String metric = request.getMetric();
//        if (metric == null || metric.isEmpty()) {
//            return;
//        }
//        Sensor sensor = sensors.get(metric);
//        if (sensor == null) {
//            responseObserver.onError(new IllegalArgumentException(String.format("metric %s does not exist; please check your configuration", metric)));
//            return;
//        }
//        long startDate = request.getStartDate();
//        long endDate = request.getEndDate();
//        Api.GetMetricHistoryResponse.Builder responseBuilder = Api.GetMetricHistoryResponse.newBuilder();
//        for (long tick = startDate; tick < endDate; tick++) {
//            responseBuilder.addValues(Api.MetricHistoryValue.newBuilder()
//                    .setTimestamp(tick)
//                    .setValue(Api.MetricValue.newBuilder().setDoubleValue(sensor.getValueAt(tick))));
//        }
//        responseObserver.onNext(responseBuilder.build());
//        responseObserver.onCompleted();
//    }
//
//    @Override
//    public void getMetricAggregate(Api.GetMetricAggregateRequest request, StreamObserver<Api.GetMetricAggregateResponse> responseObserver) {
//        String metric = request.getMetric();
//        if (metric == null || metric.isEmpty()) {
//            return;
//        }
//        Sensor sensor = sensors.get(metric);
//        if (sensor == null) {
//            responseObserver.onError(new IllegalArgumentException(String.format("metric %s does not exist; please check your configuration", metric)));
//            return;
//        }
//        long startDate = request.getStartDate();
//        long endDate = request.getEndDate();
//        Api.GetMetricAggregateResponse.Builder responseBuilder = Api.GetMetricAggregateResponse.newBuilder();
//        for (long tick = startDate; tick < endDate; tick += 10) {
//            responseBuilder.addValues(Api.MetricHistoryValue.newBuilder()
//                    .setTimestamp(tick)
//                    .setValue(Api.MetricValue.newBuilder().setDoubleValue(sensor.getValueAt(tick))));
//        }
//        responseObserver.onNext(responseBuilder.build());
//        responseObserver.onCompleted();
//    }
//}
