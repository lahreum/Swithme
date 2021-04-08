const zeroPrefix =(num, digit)=> {
    var zero = '';
    for(var i = 0; i < digit; i++) {
      zero += '0';
    }
    return (zero + num).slice(-digit);
  }

const calTime = (seconds) => {
    let hour = parseInt(seconds/3600);
    let min = parseInt((seconds%3600)/60);
    let sec = seconds%60;

    let time = 
      zeroPrefix(hour, 2) + ":" + 
      zeroPrefix(min, 2) + ":" + 
      zeroPrefix(sec, 2);
    return time
  }
const studyTime = calTime
export default studyTime