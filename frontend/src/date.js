const dateFunc=(today)=>{
    // let today = new Date();
    let year = today.getFullYear();
    let month = today.getMonth() + 1;
    let date = today.getDate();
    let hours = today.getHours();
    let min = today.getMinutes();
    let sec = today.getSeconds();
    if (month < 10) {
      month = "0" + month;
    }
    if (date < 10) {
      date = "0" + date;
    }
    if (min < 10) {
      min = "0" + min;
    }
    if (sec < 10) {
      sec = "0" + sec;
    }
    if (hours < 10) {
        hours = "0" + hours;
      }

    let datetime = `${year}-${month}-${date} ${hours}:${min}:${sec}`;
    return datetime
        

}
const date = {dateFunc}

export default date

