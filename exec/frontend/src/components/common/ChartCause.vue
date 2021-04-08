<script>
import { Pie } from 'vue-chartjs';

export default {
  props: ['countdataset'],
  extends: Pie,
  created: function() {
    console.log(this.labels);
    console.log(this.countdataset);
  },
  data() {
    return {
      chartData: {
        labels: ['핸드폰', '자리비움', '잡담', '졸음'],
        datasets: [
          {
            borderWidth: 1,
            borderColor: [
              'rgba(153, 153, 204, 1)',
              'rgba(204, 153, 153, 1)',
              'rgba(255, 204, 051, 1)',
              'rgba(051, 153, 204, 1)',
            ],
            backgroundColor: [
              'rgba(153, 153, 204, 0.5)',
              'rgba(204, 153, 153, 0.5)',
              'rgba(255, 204, 051, 0.5)',
              'rgba(051, 153, 204, 0.5)',
            ],
            data: this.countdataset,
          },
        ],
      },
      options: {
        legend: {
          display: true,
        },
        responsive: true,
        maintainAspectRatio: false,
      },
    };
  },
  mounted() {
    this.renderChart(this.chartData, this.options);
  },
};
</script>

<style></style>
