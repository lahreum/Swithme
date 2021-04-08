<script>
import { Pie } from 'vue-chartjs';

export default {
  props: ['labels', 'timedataset'],
  extends: Pie,
  created: function() {
    console.log(this.labels);
    console.log(this.timedataset);
  },
  data() {
    return {
      chartData: {
        labels: this.labels,
        datasets: [
          {
            borderWidth: 1,
            borderColor: [
              'rgba(153,204,204,1)',
              'rgba(204, 255, 051, 1)',
              'rgba(255, 153, 102, 1)',
              'rgba(153, 051,000, 1)',
              'rgba(102, 000, 255, 1)',
              'rgba(000, 051, 102, 1)',
            ],
            backgroundColor: [
              'rgba(153,204,204,0.5)',
              'rgba(204, 255, 051, 0.5)',
              'rgba(255, 153, 102, 0.5)',
              'rgba(153, 051,000, 0.5)',
              'rgba(102, 000, 255, 0.5)',
              'rgba(000, 051, 102, 0.5)',
            ],
            data: this.timedataset,
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
