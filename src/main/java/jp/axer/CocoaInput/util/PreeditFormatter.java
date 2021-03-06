package jp.axer.CocoaInput.util;

public class PreeditFormatter {
    public static Tuple3<String, Integer, Boolean> formatMarkedText(String aString, int position1, int length1) {//ユーティリティ
        StringBuilder builder = new StringBuilder(aString);
        boolean hasCaret = length1 == 0;
        if (!hasCaret) {//主文節がある
            builder.insert(position1 + length1, "§r§n");//主文節の終わりで修飾をリセットして下線修飾をセット
            builder.insert(position1, "§l");//主文節の始まりで太字修飾を追加
        } else {//主文説がない（キャレットが存在するのでそれを意識する）
            builder.insert(position1, "§r§n");
        }
        builder.insert(0, "§r§n");//最初に下線修飾をセット
        builder.append("§r");//最後に修飾をリセット
        return new Tuple3(new String(builder), position1 + 6, hasCaret);
    }
}
