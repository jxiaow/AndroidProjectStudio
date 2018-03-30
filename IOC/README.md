# 基于反射的一套IOC框架

### ContentView

1. Activity 代替手动设置setContentView()
2. Fragment 代替手动设置view

### ViewById

通过反射注入字段

### OnClick

通过反射设置View的OnClick事件

##使用方式

### Activity中使用：

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.inject(this);

        mTextView.setText("加载fragment");
    }

#### ContentView()

    @ContentView(R.layout.activity_main)
    public class MainActivity extends AppCompatActivity {}

### ViewById

    @ViewById(R.id.tv)
    private TextView mTextView;

### OnClick

    @OnClick(R.id.tv)
    private void replace(View view) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new BlankFragment())
                .commit();
    }

### Fragment中使用：

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return Injector.inject(this, inflater, container, false);
    }

其他与Activity一致