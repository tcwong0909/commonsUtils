 //js
 
 importExcel() {
          let upload = this.$refs.upload;
          let files = upload.files;
          let file = files[0];
          var formData = new FormData();
          formData.append("file", file);
          //formData.append("username","TT")
          this.uploadFileRequest("/system/basic/import",formData).then(resp=>{
            if (resp) {

            }
          })
        }