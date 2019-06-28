//��˴���
SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
@PostMapping("/import")
public RespBean importData(MultipartFile file, HttpServletRequest req) throws IOException {
    String format = sdf.format(new Date());
    String realPath = req.getServletContext().getRealPath("/upload") + format;
    File folder = new File(realPath);
    if (!folder.exists()) {
        folder.mkdirs();
    }
    String oldName = file.getOriginalFilename();
    String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
    file.transferTo(new File(folder,newName));
    String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/upload" + format + newName;
    System.out.println(url);
    return RespBean.ok("�ϴ��ɹ�!");
}

//ǰ�ˢ�  ��ͳ

<input type="file" ref="myfile">
<el-button @click="importData" type="success" size="mini" icon="el-icon-upload2">��������</el-button>


importData() {
  let myfile = this.$refs.myfile;
  let files = myfile.files;
  let file = files[0];
  var formData = new FormData();
  formData.append("file", file);
  this.uploadFileRequest("/system/basic/jl/import",formData).then(resp=>{
    if (resp) {
      console.log(resp);
    }
  })
}

//vue ���

<el-upload
  style="display: inline"
  :show-file-list="false"
  :on-success="onSuccess"
  :on-error="onError"
  :before-upload="beforeUpload"
  action="/system/basic/jl/import">
  <el-button size="mini" type="success" :disabled="!enabledUploadBtn" :icon="uploadBtnIcon">{{btnText}}</el-button>
</el-upload>



onSuccess(response, file, fileList) {
  this.enabledUploadBtn = true;
  this.uploadBtnIcon = 'el-icon-upload2';
  this.btnText = '���ݵ���';
},
onError(err, file, fileList) {
  this.enabledUploadBtn = true;
  this.uploadBtnIcon = 'el-icon-upload2';
  this.btnText = '���ݵ���';
},
beforeUpload(file) {
  this.enabledUploadBtn = false;
  this.uploadBtnIcon = 'el-icon-loading';
  this.btnText = '���ڵ���';
}
  
