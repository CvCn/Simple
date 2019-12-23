import java.util.List;

public class Perception {
    /**
     * 学习率
     */
    private final float learnRate;

    /**
     * 学习次数
     */
    private final int studyCount;

    /**
     * 阈值
     */
    private float e;

    /**
     * 权值
     */
    private float wX;

    private float wY;

    private float wZ;

    private float wF;

    /**
     * 每次学习的正确率
     */
    private float[] correctRate;

    //

    /**
     * 构造函数初始化学习率，学习次数，权值、阈值初始化为0
     * @param learnRate 学习率（取值范围 0 < learnRate < 1）
     * @param studyCount 学习次数
     */
    public Perception(float learnRate, int studyCount) {
        this.learnRate = learnRate;
        this.studyCount = studyCount;

        this.e = 0;
        this.wX = 0;
        this.wY = 0;
        this.wZ = 0;
        this.wF = 0;

        this.correctRate = new float[studyCount];
    }


    /**
     * 学习函数
     * samples[][0] 表示输入数据
     * samples[][1] 表示正确的分类结果
     * @param samples 训练数据
     */
    public void fit(List<Point> samples) {
        int sampleLength = samples.size();

        for(int i = 0 ; i < studyCount ; i ++) {
            int errorCount = 0;

            for (Point p : samples) {
                float update = learnRate * (p.result-predict(p));

                //更新权值、阈值
                wX += update * p.x;
                wY += update * p.y;
                wZ += update * p.z;
                wF += update * p.f;
                e += update;

                //计算错误次数
                if (update != 0)
                    errorCount++;
            }

            //计算此次学习的正确率
            correctRate[i] = 1 - errorCount * 1.0f / sampleLength;
        }
    }

    /**
     * 求和函数，模拟求和结点操作 ∑pw
     * @param p 输入数据
     * @return 求和结果 z
     */
    private float sum(Point p) {
        return p.x * wX + p.y * wY + p.z * wZ + p.f * wF + e;
    }

    /**
     * 激活函数，通过求和结果 z 和阈值 e 进行判断
     * @param p 输入数据
     * @return 分类结果
     */
    public int predict(Point p) {
        return sum(p) >= 0 ? 1 : -1;
    }

    /**
     * 打印正确率
     */
    public void printCorrectRate() {
        for (int i = 0 ; i < studyCount ; i ++)
            System.out.printf("第%d次学习的正确率 -> %.2f%%\n",i + 1,correctRate[i] * 100);
        System.out.printf("最终权值、阈值：%f, %f, %f, %f\n", wX, wY, wZ, e);
    }
}
