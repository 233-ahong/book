<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="addrList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="自增主键ID" align="center" prop="customerAddrId" />
      <el-table-column label="用户ID" align="center" prop="customerId" />
      <el-table-column label="邮编" align="center" prop="zip" />
      <el-table-column label="省份" align="center" prop="province" />
      <el-table-column label="城市" align="center" prop="city" />
      <el-table-column label="区" align="center" prop="district" />
      <el-table-column label="具体地址" align="center" prop="address" />
      <el-table-column label="是否默认" align="center" prop="isDefault">
        <template #default="scope">
          <dict-tag :options="isDefaultDict" :value="scope.row.isDefault"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button
            type="text"
            icon="Edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            type="text"
            icon="Delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 添加或修改用户地址对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="addrRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="邮编" prop="zip">
          <el-input v-model="form.zip" placeholder="请输入邮编" />
        </el-form-item>
<!--        <el-form-item label="省份" prop="province">
          <el-input v-model="form.province" placeholder="请输入省份" />
        </el-form-item>
        <el-form-item label="城市" prop="city">
          <el-input v-model="form.city" placeholder="请输入城市" />
        </el-form-item>
        <el-form-item label="区" prop="district">
          <el-input v-model="form.district" placeholder="请输入区" />
        </el-form-item>-->
        <el-form-item label='所在城市' prop='province'>
          <elui-china-area-dht @change="onChange"/>
        </el-form-item>
        <el-form-item label="具体地址" prop="address">
            <el-input v-model="form.address" placeholder="请输入具体地址" />
        </el-form-item>
        <el-form-item label="是否默认">
          <el-radio-group v-model="form.isDefault">
            <el-radio
              v-for="dict in isDefaultDict"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Addr">
import { EluiChinaAreaDht } from "elui-china-area-dht";
import { listAddr, getAddr, delAddr, addAddr, updateAddr } from "@/api/addr";
import { getCurrentInstance, reactive, ref } from 'vue'

const { proxy } = getCurrentInstance();
const chinaData = new EluiChinaAreaDht.ChinaArea().chinaAreaflat;

const addrList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const isDefaultDict = ref([
  {
    value: '0',
    label: '是',
    raw: {
      listClass: 'primary',
      isDefault: 'Y',
      default: true
    }
  },
  {
    value: '1',
    label: '否',
    raw: {
      listClass: 'primary',
      isDefault: 'Y',
      default: true
    }
  }
])
const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
  },
  rules: {
    zip: [
      { required: true, message: "邮编不能为空", trigger: "blur" }
    ],
    province: [
      { required: true, message: "省份不能为空", trigger: "blur" }
    ],
    city: [
      { required: true, message: "城市不能为空", trigger: "blur" }
    ],
    district: [
      { required: true, message: "区不能为空", trigger: "blur" }
    ],
    address: [
      { required: true, message: "具体地址不能为空", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询用户地址列表 */
function getList() {
  loading.value = true;
  listAddr(queryParams.value).then(response => {
    addrList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    customerAddrId: null,
    customerId: null,
    zip: null,
    province: null,
    city: null,
    district: null,
    address: null,
    isDefault: null,
    modifiedTime: null
  };
  proxy.resetForm("addrRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.customerAddrId);
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加用户地址";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _customerAddrId = row.customerAddrId || ids.value
  getAddr(_customerAddrId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改用户地址";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["addrRef"].validate(valid => {
    if (valid) {
      if (form.value.customerAddrId != null) {
        updateAddr(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        console.log(form.value)
        addAddr(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _customerAddrIds = row.customerAddrId || ids.value;
  proxy.$modal.confirm('是否确认删除用户地址编号为"' + _customerAddrIds + '"的数据项？').then(function() {
    return delAddr(_customerAddrIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

function onChange(e) {
  form.value.province = chinaData[e[0]].label
  form.value.city = chinaData[e[1]].label
  form.value.district = chinaData[e[2]].label
}
getList();
</script>
